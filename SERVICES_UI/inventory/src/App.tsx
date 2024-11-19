import React from "react";

import "./index.css";
import { Provider } from "react-redux";
import { store } from "./store";
import Test from "./test/Test";
import TestList from "./test/TestList";

const App = () => (
  <Provider store={store}>
    <div className="mt-10 text-3xl mx-auto max-w-6xl">
      <div>Name: inventory</div>
      <div>Framework: react</div>
      <div>Language: TypeScript</div>
      <div>CSS: Tailwind</div>
      <div className="bg-lime-50">State: Redux Toolkit Query</div>
    </div>
    <div>
    <Test/>
    <TestList/>
    </div>
  </Provider>
);

export default App;
