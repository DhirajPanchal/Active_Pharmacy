import React, { PropsWithChildren, useReducer, useState } from "react";
import {
  DataGridPro,
  GridColDef,
  GridFilterModel,
  GridRowSelectionModel,
  GridSortItem,
  GridSortModel,
  gridClasses,
  GridSortDirection,
} from "@mui/x-data-grid-pro";
import { alpha, styled } from "@mui/material/styles";
import { MdRefresh, MdClear } from "react-icons/md";
import {
  DEFAULT_LIST_PAYLOAD,
  ListPayload,
  ListResponse,
  SortObject,
} from "../../model/list.model";
import ActiveButton from "../../control/ActiveButton";
import { FormControlLabel, Switch } from "@mui/material";

type ActiveDataGridProps = {
  columns: GridColDef[];
  listResponse: ListResponse<any>;
  triggerRefresh?: (payload: any) => void;
  onRowSelection?: (entityId: number) => void | undefined;
};

function reducer(state: any, action: any) {
  switch (action.type) {
    case "changed_draft": {
      return {
        // draft: action.nextDraft,
        // todos: state.todos,
      };
    }
  }
}

export default function ActiveDataGrid({
  columns,
  listResponse,
  ...props
}: PropsWithChildren<ActiveDataGridProps>) {
  console.log(" < ActiveDataGrid > ");

  const ODD_OPACITY = 0.2;

  const [payload, setPayload] = useState<ListPayload>(DEFAULT_LIST_PAYLOAD);

  const [_payload, dispatch] = useReducer(reducer, DEFAULT_LIST_PAYLOAD);

  const handleRowClick = (model: GridRowSelectionModel) => {
    console.log("_ADG - Row Selection :", model);
  };

  const refresh = (localPayload: ListPayload) => {
    // console.log(" < ActiveDataGrid > PAYLOAD ****************************");
    console.log("OLD : ", payload.sort);
    console.log("NEW : ", localPayload.sort);
    if (props.triggerRefresh) {
      props.triggerRefresh(localPayload);
    }
    setPayload(localPayload);
  };

  function handleSort(model: GridSortModel) {
    console.log("_ADG - Sort ");
    console.log(model);
    if (model && model.length > 0) {
      let sortObj: SortObject = {};

      model.map((m) => {
        console.table("SORT :: " + m.field + " ( " + m.sort + " )");
        const sortfield = "" + m.field;
        const sortOrder = "" + m.sort;
        sortObj = { ...sortObj, [sortfield]: sortOrder };
      });

      const localPayload: ListPayload = {
        ...payload,
        sort: sortObj,
      };

      // console.log(localPayload);

      refresh(localPayload);
    }
  }
  function handleFilter(model: GridFilterModel) {
    console.log("_ADG - Filter :", model);
  }

  function handleModelPagination(model: any) {
    console.log("_ADG - Pagination :", model);
    const localPayload: ListPayload = {
      ...payload,
      ui_only: { ...payload.ui_only, index: model.page, size: model.pageSize },
    };
    refresh(localPayload);
  }
  const handleOnlyActive = (checked: boolean) => {
    console.log("_ADG - OnlyActice :", checked);
    const localPayload: ListPayload = {
      ...payload,
      onlyActive: checked,
    };
    refresh(localPayload);
  };
  const handleReload = () => {
    console.log("_ADG - Reload ");
    refresh(payload);
  };
  const handleClear = () => {
    console.log("_ADG - Clear ");
    const localPayload: ListPayload = { ...payload, ...DEFAULT_LIST_PAYLOAD };
    refresh(localPayload);
  };
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

  return (
    <div className="active-data-grid">
      {/* H E A D E R */}

      <div className="active-data-grid-header">
        {/* <h2>Header</h2> */}
        {props.children}
      </div>

      {/* D A T A   G R I D */}

      <StripedDataGrid
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
              pageSize: payload.ui_only.size,
              page: payload.ui_only.index,
            },
          },
        }}
        pagination={true}
        paginationModel={{
          page: payload.ui_only.index,
          pageSize: payload.ui_only.size,
        }}
        pageSizeOptions={[10, 20, 50]}
        paginationMode="server"
        onPaginationModelChange={(model) => handleModelPagination(model)}
        sortingMode="server"
        sortModel={deriveSortModel(payload)}
        onSortModelChange={(model) => handleSort(model)}
        filterMode="server"
        onFilterModelChange={(model) => handleFilter(model)}
        onRowSelectionModelChange={(model) => handleRowClick(model)}
      />

      {/* F O O T E R */}

      <div className="active-data-grid-footer">
        <FormControlLabel
          control={<Switch />}
          label="Active only"
          onChange={(event, checked) => {
            handleOnlyActive(checked);
          }}
          checked={payload.onlyActive}
          sx={{ paddingLeft: 2 }}
        />
        <ActiveButton outline rounded secondary onClick={handleReload}>
          <MdRefresh /> Refresh
        </ActiveButton>
        <ActiveButton outline rounded warning onClick={handleClear}>
          <MdClear /> Clear All Filter...
        </ActiveButton>
      </div>
    </div>
  );
}
