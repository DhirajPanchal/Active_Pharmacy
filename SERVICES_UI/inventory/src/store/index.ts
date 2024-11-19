import { configureStore } from "@reduxjs/toolkit";
import { setupListeners } from "@reduxjs/toolkit/query";
import { drugCategoryApi } from "./api/drug-category-api";

export const store = configureStore({
  reducer: {
    [drugCategoryApi.reducerPath]: drugCategoryApi.reducer,
  },
  middleware: (getDefaultMiddleware) => {
    return getDefaultMiddleware().concat(drugCategoryApi.middleware);
  },
});

setupListeners(store.dispatch);

export { useFetchDrugCategoryQuery, useFetchDrugCategoryListQuery } from "./api/drug-category-api";
