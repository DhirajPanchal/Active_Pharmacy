import React from "react";
import "react-toastify/dist/ReactToastify.css";
import { Flip, ToastContainer } from "react-toastify";
import "./index.css";
import DrugList from "./page/drug-list/DrugList";
import { Provider } from "react-redux";
import { appStore } from "./store/store";

const App = () => (
  <Provider store={appStore}>
    <div className="flex justify-center">

      <DrugList />

      <ToastContainer
        position="top-center"
        transition={Flip}
        autoClose={1200}
        pauseOnFocusLoss={false}
      />
    </div>
  </Provider>
);

export default App;
