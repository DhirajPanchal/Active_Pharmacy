import { createSlice } from "@reduxjs/toolkit";
import { Drug } from "../../../model/Drug";
import { fetchDrugList } from "./drugs-async-thunks";
import { ListResponse } from "../../../model/list.model";

type DrugsSliceType = {
  listResponse: ListResponse<Drug>;
  isLoading: boolean;
  error: any;
};

const DrugsSliceInitialState: DrugsSliceType = {
  listResponse: { content: [], count: 0 },
  isLoading: false,
  error: null,
};

const drugsSlice = createSlice({
  name: "drugs",
  initialState: DrugsSliceInitialState,
  reducers: {},
  extraReducers(builder) {
    builder.addCase(fetchDrugList.pending, (state, action) => {
      console.log("RE pending");      
      state.isLoading = true;
    });
    builder.addCase(fetchDrugList.fulfilled, (state, action) => {
      console.log("RE fulfilled");
      state.isLoading = false;
      state.listResponse = action.payload;
    });
    builder.addCase(fetchDrugList.rejected, (state, action) => {
      console.log("RE rejected");
      state.isLoading = false;
      state.error = action.error;
    });
  },
});

export default drugsSlice;
