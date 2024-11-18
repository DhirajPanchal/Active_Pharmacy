import React, { useEffect, useState } from "react";
import ActiveDataGrid from "../../component/datagrid/ActiveDataGrid";
import {
  drugCategories,
  drugClasses,
  DRUGLIST_COLUMNS,
} from "./drug-list-helper";
import { Drug } from "../../model/Drug";
import {
  DEFAULT_LIST_PAYLOAD,
  ListPayload,
  ListResponse,
} from "../../model/list.model";
import ActiveDropdown, { Option } from "../../control/ActiveDropdown";
import { fetchDrugList } from "../../store/store";
import { useThunk } from "../../hook/use-thunk";
import { appSelector } from "../../store/hooks";

export default function DrugList() {
  const [doFetchDrugList, isLoading, loadingError] = useThunk(fetchDrugList);

  useEffect(() => {
    loadData(DEFAULT_LIST_PAYLOAD);
  }, []);

  const listResponse: ListResponse<Drug> = appSelector((state) => {
    return state.drugs.listResponse;
  });

  const loadData = (payload: ListPayload) => {
    doFetchDrugList(payload);
  };

  return (
    <div className="w-fit  p-2 bg-blue-0">
      <ActiveDataGrid
        columns={DRUGLIST_COLUMNS}
        listResponse={listResponse}
        triggerRefresh={(payload) => loadData(payload)}
        loading={isLoading}
      >
        <>
          <ActiveDropdown options={drugCategories} title="Select Category" />
          <ActiveDropdown options={drugClasses} title="Select Class" />
        </>
      </ActiveDataGrid>
    </div>
  );
}
