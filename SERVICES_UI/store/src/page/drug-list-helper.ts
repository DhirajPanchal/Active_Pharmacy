import { GridColDef } from "@mui/x-data-grid-pro";
import { dateValueGetter } from "../util/utils";
import { Option } from "../control/ActiveDropdown";
import { FilterItem } from "../component/datagrid/FilterModal";

export const DRUGLIST_COLUMNS: GridColDef[] = [
  {
    field: "id",
    headerName: "ID",
    type: "string",
    width: 80,
    filterable: false,
  },
  {
    field: "drug_label_name",
    headerName: "Drug Label Name",
    type: "string",
    width: 320,
    filterable: false,
  },
  {
    field: "category_id",
    headerName: "Category Id",
    type: "string",
    width: 80,
    filterable: false,
  },
  {
    field: "category_name",
    headerName: "Category Name",
    type: "string",
    width: 160,
    filterable: false,
  },
  {
    field: "class_id",
    headerName: "Class Id",
    type: "string",
    width: 80,
    filterable: false,
  },
  {
    field: "class_name",
    headerName: "Class Name",
    type: "string",
    width: 160,
    filterable: false,
  },
  { field: "active", headerName: "Active", width: 160 },
  // { field: "deleted", headerName: "Deleted", type: "boolean", width: 80,    filterable:false },
  {
    field: "updatedOn",
    headerName: "Last Updated On",
    width: 160,
    type: "string",
    valueGetter: dateValueGetter,
    filterable: false,
  },
  // {
  //   field: "createdOn",
  //   headerName: "Created On",
  //   width: 160,
  //   type: "string",
  //   filterable: false,
  //   valueGetter: dateValueGetter,
  // },
];

export const DRUGLIST_DATA = [
  {
    id: 1,
    drug_label_name: "Amoxicillin Oral Chew 125 mg",
    category_id: 1,
    category_name: "Penicillins",
    class_id: 1,
    class_name: "Aminopenicillins",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 2,
    drug_label_name: "Amoxicillin Oral Chew 250 mg",
    category_id: 1,
    category_name: "Penicillins",
    class_id: 1,
    class_name: "Aminopenicillins",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 3,
    drug_label_name: "Penicillin G Pot Soln 40000 unit/ml",
    category_id: 1,
    category_name: "Penicillins",
    class_id: 2,
    class_name: "Natural Penicillins",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 4,
    drug_label_name: "Penicillin G Pot Soln 60000 unit/ml",
    category_id: 1,
    category_name: "Penicillins",
    class_id: 2,
    class_name: "Natural Penicillins",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 5,
    drug_label_name: "Amoxicillin-pot Clavulanate Oral Chew 400 mg",
    category_id: 1,
    category_name: "Penicillins",
    class_id: 3,
    class_name: "Penicillin Combinations",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 6,
    drug_label_name: "Amoxicillin-pot Clavulanate Oral Tabs 250 mg",
    category_id: 1,
    category_name: "Penicillins",
    class_id: 3,
    class_name: "Penicillin Combinations",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 7,
    drug_label_name: "ACTHIB INTRAMUSCULAR SOLR VIAL",
    category_id: 2,
    category_name: "Vaccines",
    class_id: 4,
    class_name: "Bacterial Vaccines",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 8,
    drug_label_name: "BCG Vaccine Injection Solr 50 mg Vial",
    category_id: 2,
    category_name: "Vaccines",
    class_id: 4,
    class_name: "Bacterial Vaccines",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 9,
    drug_label_name: "ENGERIX-B INJECTION SUSP 10 MCG/0.5ML SYRINGE",
    category_id: 2,
    category_name: "Vaccines",
    class_id: 5,
    class_name: "Viral Vaccines",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
  {
    id: 10,
    drug_label_name: "GARDASIL 9 INTRAMUSCULAR SUSP VIAL",
    category_id: 2,
    category_name: "Vaccines",
    class_id: 5,
    class_name: "Viral Vaccines",
    active: true,
    deleted: false,
    createdOn: "2024-11-04T15:36:58Z",
    updatedOn: "2024-11-04T15:36:58Z",
  },
];

export const drugCategories: Option[] = [
  { label: "Penicillins", value: "Penicillins" },
  { label: "Vaccines", value: "Vaccines" },
];

export const drugClasses: Option[] = [
  { label: "Aminopenicillins", value: "Aminopenicillins" },
  { label: "Natural Penicillins", value: "Natural Penicillins" },
  { label: "Bacterial Vaccines", value: "Bacterial Vaccines" },
  { label: "Viral Vaccines", value: "Viral Vaccines" },
];

export const DRUGLIST_FILTER_OPTIONS: FilterItem[] = [
  {
    id: 1,
    field: "drug_label_name",
    label: "Drug Label Name",
    type: "string",
    operators: ["contains", "equals"],
  },
  {
    id: 2,
    field: "category_name",
    label: "Category Name",
    type: "string",
    operators: ["contains", "equals"],
  },
  {
    id: 3,
    field: "class_name",
    label: "Class Name",
    type: "string",
    operators: ["contains", "equals"],
  },
  {
    id: 4,
    field: "active",
    label: "Active",
    type: "boolean",
    operators: ["equals"],
    values: [true, false],
  },
];
