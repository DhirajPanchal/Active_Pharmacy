export type SortObject = { [key: string]: string };

export type FilterItem = { field: string; operator: string; value?: string };

export interface ListPayload {
  sort?: SortObject;
  filter?: FilterItem[];
  onlyActive: boolean;
  ui_only: {
    index: number;
    size: number;
  };
}

export const DEFAULT_LIST_PAYLOAD: ListPayload = {
  filter: [{ field: "category_name", operator: "contains", value: "A" }, { field: "class_name", operator: "contains", value: "B" }],
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
