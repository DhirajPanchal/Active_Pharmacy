import React from "react";
import "./index.css";

const StoreApp = React.lazy(() => import("store/StoreApp"));
const InventoryApp = React.lazy(() => import("inventory/InventoryApp"));
const App = () => (
  <div className="m-2">
    <header className="bg-blue-0 float-end h-18 p-2">
      {/* <h1 className="font-bold">APP</h1> */}
      <img src="/activeRx_Pharmacy_c02.png" alt="Logo" className="logo-image" />
    </header>
    <StoreApp />
    <InventoryApp />
  </div>
);

export default App;
