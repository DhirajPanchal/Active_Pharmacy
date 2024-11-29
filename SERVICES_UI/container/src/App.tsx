import React, { Suspense, useState } from "react";
import "./index.css";
import NavBar from "./component/NavBar";
import Login from "./component/Login";
import Registration from "./component/Registration";
import Loader from "./component/Loader";
import { Routes, Route, useNavigate } from "react-router-dom";
import NotFound from "./component/NotFound";
import Profile from "./component/Profile";
import { IUser } from "./model/auth.model";
import { Provider } from "react-redux";
import { appStore } from "./store/store";

const StoreRemoteApp = React.lazy(() => import("store/StoreApp"));
// const InventoryRemoteApp = React.lazy(() => import("inventory/InventoryApp"));

function App() {
  const [isAuthenticated, setAuthenticated] = useState<boolean>(false);
  const [pricipal, setPrincipal] = useState<IUser>();
  const navigation = useNavigate();


  return (
    <Provider store={appStore}>
      <div>
        <NavBar
          
        />
        <Suspense fallback={<Loader />}>
          <Routes>
            <Route
              path="/"
              element={<Login />}
            />
            <Route path="/profile" element={<Profile pricipal={pricipal} />} />
            <Route path="/registration" element={<Registration />} />
            <Route
              path="/login"
              element={<Login  />}
            />
            <Route path="/store/*" element={<StoreRemoteApp />} />
            {/* <Route path="/inventory/*" element={<InventoryRemoteApp />} /> */}
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Suspense>
      </div>
    </Provider>
  );
}

export default App;
