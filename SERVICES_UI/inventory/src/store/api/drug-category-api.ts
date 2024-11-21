import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

const drugCategoryApi = createApi({
  reducerPath: "drugcategory",
  baseQuery: fetchBaseQuery({
    baseUrl: "http://localhost:8022/active-pharmacy/inventory/api/v1/drug-category",
  }),
  endpoints(builder) {
    return {
      fetchDrugCategory: builder.query({
        query: (id: number) => {
          return {
            url: `/${id}`,
            method: "GET",
          };
        },
      }),
      fetchDrugCategoryList: builder.query({
        query: () => {
          return {
            url: `/list`,
            method: "POST",
          };
        },
      }),




    };
  },
});

export const { useFetchDrugCategoryQuery, useFetchDrugCategoryListQuery } = drugCategoryApi;
export { drugCategoryApi };
