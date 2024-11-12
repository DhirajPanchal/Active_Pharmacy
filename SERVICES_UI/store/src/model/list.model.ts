import { GridFilterItem } from "@mui/x-data-grid-pro";

export type SortObject = { [key: string]: string };

// export type FilterItem = { id:string, field: string; operator: string; value?: string };

export interface ListPayload {
  sort?: SortObject;
  filter?: GridFilterItem[];
  onlyActive: boolean;
  ui_only: {
    index: number;
    size: number;
  };
}

export const DEFAULT_LIST_PAYLOAD: ListPayload = {
  filter: [{ field: "drug_label_name", operator: "contains", value: "DDD", id:1 }],
  sort: { id: "asc" },
  onlyActive: false,
  ui_only: {
    index: 0,
    size: 10,
  },
};

export interface ListResponse<T> {
  content: T[];
  count: number;
}

