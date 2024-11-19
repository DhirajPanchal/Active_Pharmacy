import React from "react";
import { useFetchDrugCategoryListQuery } from "../store";

export default function TestList() {
  const { data, error, isFetching } = useFetchDrugCategoryListQuery(1);
  console.log(data, error, isFetching);

  let content;
  if (isFetching) {
    content = <div>Loading ....</div>;
  } else if (error) {
    content = <div>Error loading drug.</div>;
  } else {
    content = data?.map((dc: any) => {
      return <p key={dc.id}>{dc.name}</p>;
    });
  }

  return (
    <div className="contain">
      <h1 className="border-2"> Drug Category List</h1>
      <hr />
      {content}
    </div>
  );
}
