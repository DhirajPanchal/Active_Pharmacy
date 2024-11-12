import React from "react";
import ActiveButton from "../../control/ActiveButton";

type FilterModalProps = {
  isOpen: boolean;
  onClose: () => void;
};

const FilterModal = ({ isOpen, onClose }: FilterModalProps) => {
  if (!isOpen) return null;

  return (
    <div className="adg-filter">
      <div className="adg-filter-body bg-yellow-50 border-2 border-gray-400">
        <div className="adg-filter-title border-b-2 border-gray-400">
          <h1 className="text-xl font-bold">Filter</h1>
        </div>
        <div className="adg-filter-form">
          <p>asdadawd</p>
          <p>asdadawd</p>
          <p>asdadawd</p>
          <p>asdadawd</p>
          <p>asdadawd</p>
        </div>
        <div className="adg-filter-toolbar border-t-2 border-gray-400">
          <ActiveButton outline rounded secondary onClick={onClose}>
            Cancel
          </ActiveButton>
          <ActiveButton outline rounded secondary>
            Apply Filter
          </ActiveButton>
        </div>
      </div>
    </div>
  );
};

export default FilterModal;
