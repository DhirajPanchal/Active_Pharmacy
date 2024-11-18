import { createAsyncThunk } from "@reduxjs/toolkit";
import ExternalInterface from "../../../service/ExternalInterface";
import { ListPayload } from "../../../model/list.model";

const fetchDrugList: any = createAsyncThunk(
  "drugs/list",
  async (payload: ListPayload) => {
    const data = await ExternalInterface.loadDrugList(payload);
    console.log(" fetchDrugList :: ");
    console.log(data);
    return data;
  }
);

export { fetchDrugList };
