import React from "react";
import "react-toastify/dist/ReactToastify.css";
import { Flip, ToastContainer } from "react-toastify";
import "./index.scss";
import DrugList from "./page/DrugList";

const App = () => (
  <div className="">
    <header className="bg-blue-0 h-20 p-6 border-b-2 border-b-blue-640 border-dashed ">
      <h1 className="font-bold">STORE</h1>
    </header>

    <DrugList />

    <ToastContainer
      position="top-center"
      transition={Flip}
      autoClose={1200}
      pauseOnFocusLoss={false}
    />
  </div>
);

export default App;
