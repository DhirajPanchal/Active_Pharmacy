export type SortObject = { [key: string]: string };
export type FilterObject = { [key: string]: string };

export interface ListPayload {
  sort?: SortObject;
  filter?: FilterObject;
  onlyActive: boolean;
  ui_only: {
    index: number;
    size: number;
  };
}

export const DEFAULT_LIST_PAYLOAD: ListPayload = {
  filter: {},
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
