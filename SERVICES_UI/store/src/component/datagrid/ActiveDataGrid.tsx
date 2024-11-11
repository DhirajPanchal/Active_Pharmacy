import React, { PropsWithChildren, useReducer } from "react";
import {
  DataGridPro,
  GridColDef,
  GridFilterModel,
  GridRowSelectionModel,
  GridSortItem,
  GridSortModel,
  gridClasses,
  GridSortDirection,
  GridFilterItem,
} from "@mui/x-data-grid-pro";
import { alpha, styled } from "@mui/material/styles";
import { MdRefresh, MdClear } from "react-icons/md";
import {
  DEFAULT_LIST_PAYLOAD,
  FilterItem,
  ListPayload,
  ListResponse,
  SortObject,
} from "../../model/list.model";
import ActiveButton from "../../control/ActiveButton";
import { FormControlLabel, Switch } from "@mui/material";

type ActiveDataGridProps = {
  columns: GridColDef[];
  listResponse: ListResponse<any>;
  triggerRefresh: (payload: any) => void;
  onRowSelection?: (entityId: number) => void | undefined;
};

export default function ActiveDataGrid({
  columns,
  listResponse,
  triggerRefresh,
  ...props
}: PropsWithChildren<ActiveDataGridProps>) {
  console.log(" < ActiveDataGrid > ");

  const ODD_OPACITY = 0.2;

  const [_payload, dispatch] = useReducer(reducer, DEFAULT_LIST_PAYLOAD);

  const handleRowClick = (model: GridRowSelectionModel) => {
    console.log("_ADG - Row Selection :", model);
  };

  function reducer(state: any, action: any) {
    console.log(" CHANGE ", action);
    // console.log(state);

    let cache: any;
    switch (action.type) {
      case REFRESH: {
        cache = {
          ...state,
        };
        break;
      }
      case CLEAR_ALL_FILTERS: {
        cache = {
          ...state,
          ...DEFAULT_LIST_PAYLOAD,
        };
        break;
      }
      case ACTIVE_ONLY: {
        cache = {
          ...state,
          onlyActive: action.checked,
        };
        break;
      }
      case PAGINATION: {
        cache = {
          ...state,
          ui_only: {
            ...state.ui_only,
            index: action.model.page,
            size: action.model.pageSize,
          },
        };
        break;
      }
      case SORT: {
        cache = {
          ...state,
          sort: action.sortObj,
        };
        break;
      }
      case FILTER: {
        cache = {
          ...state,
          filter: action.list,
        };
        break;
      }
    }
    // console.log("Payload Changed ", cache);
    if (cache && triggerRefresh) {
      triggerRefresh(cache);
      return cache;
    } else {
      return state;
    }
  }

  function handleSort(model: GridSortModel) {
    if (model && model.length > 0) {
      let sortObj: SortObject = {};

      model.map((m) => {
        console.table("SORT :: " + m.field + " ( " + m.sort + " )");
        const sortfield = "" + m.field;
        const sortOrder = "" + m.sort;
        sortObj = { ...sortObj, [sortfield]: sortOrder };
      });

      dispatch({ type: SORT, sortObj });
    }
  }

  const deriveSortModel = (payload: ListPayload): GridSortItem[] => {
    let list: GridSortItem[] = [];
    if (payload && payload.sort) {
      const sortObj = payload.sort;
      const sortKeys = Object.keys(sortObj);
      sortKeys.map((key) => {
        list.push({ field: key, sort: sortObj[key] as GridSortDirection });
      });
    }
    return list;
  };

  const deriveFilterModel = (payload: ListPayload): GridFilterModel => {
    let list: GridFilterItem[] = [];
    if (payload && payload.filter && payload.filter.length > 0) {
      payload.filter.map((fi) => list.push(fi));
    }
    console.log("FIs : ", list);
    //GridFilterModel
    return { items: list };
  };

  function handleFilter(model: GridFilterModel) {
    // console.log("_ADG - Filter :", model);
    console.log(
      "--------------------------------------------------------------------------"
    );

    if (model && model.items) {
      let list: FilterItem[] = [];
      if (model.items.length > 0) {
        model.items.map((item) => {
          console.log(item);

          if (item && item.field && item.operator && item.value) {
          list.push({
            field: item.field,
            operator: item.operator,
            value: item.value,
          });
          }
        });
      }
      // console.log("LIST  ", list);
      // console.log("STATE ", _payload.filter);
      // if (list.length !== _payload.filter.length) {
      //   console.log(">>>");
      //   dispatch({ type: FILTER, list });
      // }
      const l1 = [...list];
      const l2 = [..._payload.filter];
      console.log(l1);
      console.log(l2);

      const l3 = l1.filter(
        (a: FilterItem) =>
          !l2.some(
            (b: FilterItem) =>
              a.field === b.field &&
              a.operator === b.operator &&
              a.value === b.value
          )
      );
      console.log(l3);
      if (l3.length > 0 || l1.length !== l2.length) {
        console.log(">>>");
        dispatch({ type: FILTER, list });
      }
    }
  }

  const StripedDataGrid = styled(DataGridPro)(({ theme }) => ({
    [`& .${gridClasses.row}.even`]: {
      backgroundColor: theme.palette.grey[200],
      "&:hover": {
        backgroundColor: alpha(theme.palette.primary.main, ODD_OPACITY),
        "@media (hover: none)": {
          backgroundColor: "transparent",
        },
      },
      "&.Mui-selected": {
        backgroundColor: alpha(
          theme.palette.primary.main,
          ODD_OPACITY + theme.palette.action.selectedOpacity
        ),
        "&:hover": {
          backgroundColor: alpha(
            theme.palette.primary.main,
            ODD_OPACITY +
              theme.palette.action.selectedOpacity +
              theme.palette.action.hoverOpacity
          ),
          // Reset on touch devices, it doesn't add specificity
          "@media (hover: none)": {
            backgroundColor: alpha(
              theme.palette.primary.main,
              ODD_OPACITY + theme.palette.action.selectedOpacity
            ),
          },
        },
      },
    },
  }));

  return (
    <div className="active-data-grid">
      {/* H E A D E R */}

      <div className="active-data-grid-header">
        {/* <h2>Header</h2> */}
        {props.children}
      </div>

      {/* D A T A   G R I D */}

      <StripedDataGrid
        sx={{ height: "520px" }}
        columns={columns}
        rows={listResponse.content ? listResponse.content : []}
        rowCount={listResponse.count ? listResponse.count : 0}
        rowHeight={38}
        getRowClassName={(params) =>
          params.indexRelativeToCurrentPage % 2 === 0 ? "even" : "odd"
        }
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: _payload.ui_only.size,
              page: _payload.ui_only.index,
            },
          },
        }}
        pagination={true}
        paginationModel={{
          page: _payload.ui_only.index,
          pageSize: _payload.ui_only.size,
        }}
        pageSizeOptions={[10, 20, 50]}
        paginationMode="server"
        onPaginationModelChange={(model) =>
          dispatch({ type: PAGINATION, model })
        }
        sortingMode="server"
        sortModel={deriveSortModel(_payload)}
        onSortModelChange={(model) => handleSort(model)}
        filterMode="server"
        filterDebounceMs={5000}
        filterModel={deriveFilterModel(_payload)}
        onFilterModelChange={(model) => handleFilter(model)}
        onRowSelectionModelChange={(model) => handleRowClick(model)}
      />

      {/* F O O T E R */}

      <div className="active-data-grid-footer">
        <FormControlLabel
          control={<Switch />}
          label="Active only"
          onChange={(_event, checked) => {
            dispatch({ type: ACTIVE_ONLY, checked });
          }}
          checked={_payload.onlyActive}
          sx={{ paddingLeft: 2 }}
        />
        <ActiveButton
          outline
          rounded
          secondary
          onClick={() => dispatch({ type: REFRESH })}
        >
          <MdRefresh /> Refresh
        </ActiveButton>
        <ActiveButton
          outline
          rounded
          warning
          onClick={() => dispatch({ type: CLEAR_ALL_FILTERS })}
        >
          <MdClear /> Clear All Filter...
        </ActiveButton>
      </div>
    </div>
  );
}

const PAGINATION: string = "PAGINATION";
const SORT: string = "SORT";
const FILTER: string = "FILTER";
const ACTIVE_ONLY: string = "ACTIVE_ONLY";
const REFRESH: string = "REFRESH";
const CLEAR_ALL_FILTERS: string = "CLEAR_ALL_FILTERS";
