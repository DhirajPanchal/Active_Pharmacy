export interface Drug {
  id: number;
  drug_label_name: string;
  category_id: number;
  category_name: string;
  class_id: number;
  class_name: string;
  active: boolean;
  deleted: boolean;
  createdOn: string;
  updatedOn: string;
}