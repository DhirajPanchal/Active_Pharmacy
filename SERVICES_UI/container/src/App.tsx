import React, { Suspense, useState } from "react";
import "./index.css";
import NavBar from "./component/NavBar";
import Login from "./component/Login";
import Registration from "./component/Registration";
import Loader from "./component/Loader";
import { Routes, Route, useNavigate } from "react-router-dom";
import NotFound from "./component/NotFound";
import Profile from "./component/Profile";
import Home from "./component/Home";
import ExternalInterface from "./service/ExternalInterface";
import { IUser } from "./model/IUser";

// const StoreRemoteApp = React.lazy(() => import("store/StoreApp"));
// const InventoryRemoteApp = React.lazy(() => import("inventory/InventoryApp"));

function App() {
  const [isAuthenticated, setAuthenticated] = useState<boolean>(false);
  const [pricipal, setPrincipal] = useState<IUser>();
  const navigation = useNavigate();

  const handleLogin = (token: string) => {
    console.log("handleLogin");

    if (token && token.length > 0) {
      setAuthenticated(true);
      sessionStorage.setItem("TOKEN", token);
      navigation("/profile");
    }

    ExternalInterface.userProfile().then((data: any) => {
      console.log("DATA :::");
      console.log(data);

      const user: IUser = {
        firstName: data?.user?.firstName,
        lastName: data?.user?.lastName,
        email: data?.user?.email,
        id: data?.user?.id,
        roles: data?.roles,
        business: false,
      };
      console.log(" pricipal :: ");
      console.log(user);

      setPrincipal(user);
    });
  };

  const handleLogout = () => {
    console.log("_handleLogout");
    setAuthenticated(false);
    setPrincipal(undefined);
    sessionStorage.removeItem("TOKEN");
  };

  return (
    <div>
      <div>
        <NavBar
          isAuthenticated={isAuthenticated}
          pricipal={pricipal}
          onLogout={handleLogout}
        />
        <Suspense fallback={<Loader />}>
          <Routes>
            <Route
              path="/"
              element={<Login onLogin={(token) => handleLogin(token)} />}
            />
            <Route path="/profile" element={<Profile pricipal={pricipal} />} />
            <Route path="/registration" element={<Registration />} />
            <Route
              path="/login"
              element={<Login onLogin={(token) => handleLogin(token)} />}
            />
            {/* <Route path="/store/*" element={<StoreRemoteApp />} />
          <Route path="/inventory/*" element={<InventoryRemoteApp />} /> */}
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Suspense>
      </div>
    </div>
  );
}

export default App;
