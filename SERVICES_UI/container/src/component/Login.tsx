import React, { useState } from "react";
import { ILogin } from "../model/auth.model";
import { useThunk } from "../store/hooks";
import { userLogin, userProfile } from "../store/store";
import { useNavigate } from "react-router-dom";
import { unwrapResult } from "@reduxjs/toolkit";
import BusySpinner from "../control/BusySpinner";

const defaultFormData: ILogin = {
  username: "",
  password: "",
  ui_only_errors: [],
};

export default function Login() {
  const navigation = useNavigate();

  const [doLogin, isLoading, error] = useThunk(userLogin);

  const [doProfile] = useThunk(userProfile);

  const [formData, setFormData] = useState<ILogin>(defaultFormData);

  const formSubmit = async (event: any) => {
    event.preventDefault();
    console.log(formData);

    const payload: ILogin = { ...formData };
    payload.password = payload.password.trim();
    payload.username = payload.username.trim();

    const resultAction = await doLogin(payload);
    try {
      unwrapResult(resultAction);
      navigation("/profile");
      doProfile();
    } catch (err) {}
  };

  const updateValue = (key: string, value: any) => {
    // console.log(`-------------------------------${key} :: ${value}`);
    let input: any = { ...formData };
    input[key] = value;
    setFormData(input);
  };

  return (
    <div className="font-[sans-serif]">
      <div className="mt-12 flex fle-col items-center justify-center py-6 px-4">
        <div className="grid md:grid-cols-2 items-center gap-120 max-w-6xl w-full ">
          <div className="hidden lg:block w-full">
            <h1 className="animate-pulse lg:text-8xl text-4xl font-extrabold lg:leading-[55px] tracking-wider text-opacity-60 text-pretty text-green-600 ">
              Seamless login & exclusive &#8478; pricing
            </h1>
          </div>

          <div className="max-w-md md:ml-auto w-full  ">
            <form
              onSubmit={formSubmit}
              className="bg-gray-200 p-4 border-r-8 border-4"
            >
              <BusySpinner
                isLoading={isLoading}
                isError={error}
                className="float-right"
              />
              <h2 className="text-blue-900 text-3xl font-extrabold mb-8">
                Login
              </h2>

              <div className="space-y-4">
                <div>
                  <input
                    name="email"
                    type="email"
                    required
                    className="bg-white w-full text-lg px-4 py-3.5 rounded-md outline-blue-900 "
                    placeholder="Email address"
                    onChange={(e) => updateValue("username", e.target.value)}
                  />
                </div>
                <div>
                  <input
                    name="password"
                    type="password"
                    required
                    autoComplete="true"
                    className="bg-white w-full text-lg px-4 py-3.5 rounded-md outline-blue-900 "
                    placeholder="Password"
                    onChange={(e) => updateValue("password", e.target.value)}
                  />
                </div>
                <div className="flex flex-wrap items-center justify-between gap-4">
                  <div className="flex items-center">
                    <input
                      id="remember-me"
                      name="remember-me"
                      type="checkbox"
                      className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                    />
                    <label className="ml-3 block text-sm text-gray-800">
                      Remember me
                    </label>
                  </div>
                  <div className="text-sm">
                    <a className="text-blue-900 hover:text-blue-500 font-semibold">
                      Forgot your password?
                    </a>
                  </div>
                </div>
              </div>

              <div className="m-0 p-0">
                <p className="text-red-600 m-0 p-0 text-b">{error?.message}</p>
                {error?.errors?.map((errorLine: any, index: number) => (
                  <p key={index} className="text-red-600 m-0 p-0">
                    {errorLine}
                  </p>
                ))}
              </div>

              <div className="!mt-4">
                <button
                  type="submit"
                  className="w-full shadow-xl py-2.5 px-4 text-sm font-semibold rounded text-white bg-blue-900 hover:bg-blue-600 focus:outline-none"
                >
                  Log in
                </button>
              </div>

              <div className="space-x-6 flex justify-center">
                <p className="text-sm mt-4 text-gray-800">
                  Don't have an account{" "}
                  <a className="text-blue-900 font-semibold hover:underline ml-1">
                    Register here
                  </a>
                </p>
              </div>
            </form>
            <div className="flex justify-evenly border-4 border-dashed border-gray-200 hover:bg-blue-100 mt-4">
              <p className="mt-8 text-sp text-gray-400">
                <a className=" font-semibold hover:underline ml-1">
                  BUSINESS USER LOGIN
                </a>
              </p>

              <img
                src="/keycloak_01.png"
                alt="Logo"
                className="keycload-image"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
