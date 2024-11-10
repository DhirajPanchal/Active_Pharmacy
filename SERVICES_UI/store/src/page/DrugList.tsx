import React, { useEffect, useState } from "react";
import ActiveDataGrid from "../component/datagrid/ActiveDataGrid";
import { drugCategories, drugClasses, DRUGLIST_COLUMNS, DRUGLIST_DATA } from "./drug-list-helper";
import { Drug } from "../model/Drug";
import {
  DEFAULT_LIST_PAYLOAD,
  ListPayload,
  ListResponse,
} from "../model/list.model";
import ActiveButton from "../control/ActiveButton";
import ActiveDropdown, { Option } from "../control/ActiveDropdown";
import ExternalInterface from "../service/ExternalInterface";

export default function DrugList() {
  // const listResponse: ListResponse<Drug> = {
  //   content: DRUGLIST_DATA,
  //   count: DRUGLIST_DATA.length,
  // };

  const [listResponse, setListResponse] = useState<ListResponse<Drug>>({
    content: [],
    count: 0,
  });

  useEffect(() => {
    loadHandler(DEFAULT_LIST_PAYLOAD);
  }, []);



  const loadHandler = (payload: ListPayload) => {
    ExternalInterface.loadDrugList(payload)
      .then((data) => {
        setListResponse(data);
      })
      .catch(() => {});
  };

  return (
    <div className="w-fit  p-2 bg-blue-0">
      <ActiveDataGrid
        columns={DRUGLIST_COLUMNS}
        listResponse={listResponse}
        triggerRefresh={(payload) => loadHandler(payload)}
      >
        <>
          <ActiveDropdown options={drugCategories} title="Select Category" />
          <ActiveDropdown options={drugClasses} title="Select Class" />
        </>
      </ActiveDataGrid>
    </div>
  );
}
