import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

const drugCategoryApi = createApi({
  reducerPath: "drugcategory",
  baseQuery: fetchBaseQuery({
    baseUrl:
      "http://localhost:8010/active-pharmacy/inventory/api/v1/drug-category",

    prepareHeaders: (headers) => {
      const token = sessionStorage.getItem("TOKEN");
      if (token) {
        headers.set("Authorization", `Bearer ${token}`);
      }
      return headers;
    },
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

export const { useFetchDrugCategoryQuery, useFetchDrugCategoryListQuery } =
  drugCategoryApi;
export { drugCategoryApi };
