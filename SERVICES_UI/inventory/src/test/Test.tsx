import React from "react";
import { useFetchDrugCategoryQuery } from "../store";

export default function Test() {
  const { data, error, isFetching } = useFetchDrugCategoryQuery(1);
  console.log(data, error, isFetching);


  

  let content;
  if (isFetching) {
    content = <div>Loading ....</div>;
  } else if (error) {
    content = <div>Error loading drug.</div>;
  } else {
    content = <div>{data.name} </div>;
  }

  return (
    <div className="contain">
      <h1> Drug Category by ID</h1>
      <hr />
      {content}
    </div>
  );
}
