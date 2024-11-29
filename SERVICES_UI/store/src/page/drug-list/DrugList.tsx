import React, { useEffect, useState } from "react";
import ActiveDataGrid from "../../component/datagrid/ActiveDataGrid";
import { DRUGLIST_COLUMNS } from "./drug-list-helper";
import { ListPayload } from "../../model/list.model";
import {
  fetchDrugCategories,
  fetchDrugClasses,
  fetchDrugList,
} from "../../store/store";
import { useThunk } from "../../hook/use-thunk";
import { appSelector } from "../../store/hooks";
import { Autocomplete, TextField } from "@mui/material";
import { shallowEqual } from "react-redux";
import ViewToggle from "../../control/ViewToggle";
import GridView from "../../component/grid-view/GridView";

export default function DrugList() {
  // console.log("< DrugList > ");
  const [isGrid, setIsGrid] = useState<boolean>(true);
  const [doFetchDrugList, drugsWIP, drugsERR] = useThunk(fetchDrugList);
  const [dofetchDrugCategories, CAT_WIP, CAT_ERR] =
    useThunk(fetchDrugCategories);
  const [dofetchDrugClasses, CLS_WIP, CLS_ERR] = useThunk(fetchDrugClasses);

  const [selectedCAT, setSelectedCAT] = useState<number>();
  const [selectedCLS, setSelectedCLS] = useState<number>();

  useEffect(() => {
    //loadDrugs(DEFAULT_LIST_PAYLOAD);
    console.log("< DrugList > ***");
    dofetchDrugCategories();
    dofetchDrugClasses();
  }, []);

  const [listResponse, drugCategories, drugClasses] = appSelector((state) => {
    return [
      state.drugs.listResponse,
      state.drugs.drugCategories,
      state.drugs.drugClasses,
    ];
  }, shallowEqual);

  const loadDrugs = (payload: ListPayload) => {
    doFetchDrugList(payload);
  };

  const handleCategoryInputChange = (value: any) => {
    // console.log("__handleCategoryInputChange");
    // console.log(value);
    // dofetchDrugCategories(value);
  };
  const handleCategoryChange = (value: any) => {
    console.log("__handleCategoryChange");
    console.log(value);
    setSelectedCAT(value?.value);
  };
  const handleClassInputChange = (value: any) => {
    // console.log("__handleClassInputChange");
    // console.log(value);
    // dofetchDrugClasses(value);
  };

  const handleClassChange = (value: any) => {
    console.log("__handleClassChange");
    console.log(value);
    setSelectedCLS(value?.value);
  };

  const handleViewChange = (view: boolean) => {
    setIsGrid(view);
  };

  return (
    //className="w-fit p-2"  bg-yellow-200 border-4 border-blue-600
    <div className=" flex flex-col w-full mx-2 px-2">
      <div className="flex justify-end m-2 pr-2">
        <ViewToggle viewChange={(view) => handleViewChange(view)} />
      </div>

      <div className="p-2">
        {isGrid && (

            <GridView
              content={listResponse?.content}
              triggerRefresh={(payload) => loadDrugs(payload)}
            />
 
        )}

        {!isGrid && (
          <ActiveDataGrid
            columns={DRUGLIST_COLUMNS}
            listResponse={listResponse}
            triggerRefresh={(payload) => loadDrugs(payload)}
            loading={drugsWIP}
            error={drugsERR}
          >
            <>
              <Autocomplete
                disablePortal
                options={drugCategories ? drugCategories : []}
                sx={{ width: 200, height: 64 }}
                onChange={(event, value) => handleCategoryChange(value)}
                onInputChange={(event, value) =>
                  handleCategoryInputChange(value)
                }
                renderInput={(params) => (
                  <TextField {...params} label="Select Drug Category" />
                )}
              />
              {selectedCAT}
              <Autocomplete
                disablePortal
                options={drugClasses ? drugClasses : []}
                onChange={(event, value) => handleClassChange(value)}
                onInputChange={(event, value) => handleClassInputChange(value)}
                sx={{ width: 200, height: 64 }}
                renderInput={(params) => (
                  <TextField {...params} label="Select Drug Class" />
                )}
              />
              {selectedCLS}
            </>
          </ActiveDataGrid>
        )}
      </div>
    </div>
  );
}
