import React, { useEffect, useState } from "react";
import {
  DEFAULT_GRID_PAYLOAD,
  DEFAULT_LIST_PAYLOAD,
  ListPayload,
} from "../../model/list.model";

type GridViewProps = {
  content: any[];
  triggerRefresh?: (payload: any) => void;
  loading?: boolean;
  error?: boolean;
};
//
export default function GridView({ content, triggerRefresh }: GridViewProps) {
  //   console.log(" < GridView > ");
  //drugs/002.jpg
  const [payload, setPayload] = useState<ListPayload>(DEFAULT_GRID_PAYLOAD);

  useEffect(() => {
    console.log(" < GridView > useEffect ");
    if (triggerRefresh) {
      triggerRefresh(payload);
    }
  }, [payload]);

  const imageUrl = (id: number): string => {
    if (id < 10) {
      return `drugs/00${id}.jpg`;
    } else if (id < 100) {
      return `drugs/0${id}.jpg`;
    }

    return "drugs/001.jpg";
  };

  return (
    <div className="entity-table-wrapper mx-auto lg:p-4 sm:p-2">
      <div className="sm:grid lg:grid-cols-5 sm:grid-cols-2 gap-8">
        {/* C A R D  START */}

        {content?.map((item, index) => (
          <div
            key={index}
            className="bg-white hover:bg-gray-200 text-gray-600 transition duration-300 max-w-sm rounded overflow-hidden shadow-md border-1 border-gray-100"
          >
            <div className="py-4 px-4">
              <img src={imageUrl(item.id)} className="h-48" />
              <a>
                <h5 className="text-lg my-3 font-semibold">{item?.name}</h5>
              </a>
              <p className="mb-2 text-sm text-gray-600">
                {item?.category_name} | {item?.class_name}
              </p>
            </div>
          </div>
        ))}
        {/* C A R D  END */}
      </div>
    </div>
  );
}
