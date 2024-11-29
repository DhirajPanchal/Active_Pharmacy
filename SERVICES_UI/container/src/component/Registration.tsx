import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { IRegistration } from "../model/auth.model";
import { useThunk } from "../store/hooks";
import { userRegistration } from "../store/store";
import { unwrapResult } from "@reduxjs/toolkit";
import BusySpinner from "../control/BusySpinner";

const defaultFormData: IRegistration = {
  email: "",
  firstName: "",
  lastName: "",
  password: "",
  matchingPassword: "",
  ui_only_errors: [],
};

export default function Registration() {

  const navigation = useNavigate();

  const [doRegistration, isLoading, error] = useThunk(userRegistration);

  const [formData, setFormData] = useState<IRegistration>(defaultFormData);

  const formSubmit = async (event: any) => {
    event.preventDefault();
    console.log(formData);
    const payload: IRegistration = { ...formData };
    payload.firstName = payload.firstName.trim();
    payload.lastName = payload.lastName.trim();
    payload.password = payload.password.trim();
    payload.matchingPassword = payload.matchingPassword.trim();

    //doRegistration(payload);
    const resultAction = await doRegistration(payload);
    try {
      unwrapResult(resultAction);
      navigation("/login");
    } catch (err) {}
  };

  const updateValue = (key: string, value: any) => {
    // console.log(`-------------------------------${key} :: ${value}`);
    let input: any = { ...formData };
    input[key] = value;
    if (
      input &&
      input.password &&
      input.password != "" &&
      input.matchingPassword &&
      input.matchingPassword != "" &&
      input.password !== input.matchingPassword
    ) {
      input.ui_only_errors = ["Password don't match"];
    } else {
      input.ui_only_errors = [];
    }
    setFormData(input);
  };

  return (
    <div className="font-[sans-serif]">
      <div className="mt-12 flex fle-col items-center justify-center py-6 px-4">
        <div className="grid md:grid-cols-2 items-center gap-120 max-w-6xl w-full">
          <div className="hidden lg:block">
            <h3 className="animate-pulse lg:text-8xl text-4xl font-extrabold lg:leading-[55px] tracking-wider text-opacity-50 text-pretty text-green-600">
              Explore Alternative & Generic drugs
            </h3>
          </div>

          <form
            onSubmit={formSubmit}
            className="max-w-md md:ml-auto w-full bg-gray-200 p-4 border-r-8 "
          >
            <BusySpinner
              isLoading={isLoading}
              isError={error}
              className="float-right"
            />
            <h2 className="text-blue-900 text-3xl font-extrabold mb-8">
              Registration
            </h2>

            <div className="space-y-4">
              <div>
                <input
                  name="email"
                  type="email"
                  required
                  className="bg-white w-full text-lg px-4 py-3.5 rounded-md outline-blue-900 "
                  placeholder="Email address"
                  onChange={(e) => updateValue("email", e.target.value)}
                />
              </div>

              <div>
                <input
                  name="firstName"
                  type="text"
                  required
                  className="bg-white w-full text-lg px-4 py-3.5 rounded-md outline-blue-900 "
                  placeholder="First Name"
                  onChange={(e) => updateValue("firstName", e.target.value)}
                />
              </div>

              <div>
                <input
                  name="lastName"
                  type="text"
                  required
                  className="bg-white w-full text-lg px-4 py-3.5 rounded-md outline-blue-900 "
                  placeholder="Last Name"
                  onChange={(e) => updateValue("lastName", e.target.value)}
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

              <div>
                <input
                  name="password"
                  required
                  autoComplete="true"
                  className="bg-white w-full text-lg px-4 py-3.5 rounded-md outline-blue-900 "
                  placeholder="Re enter password"
                  onChange={(e) =>
                    updateValue("matchingPassword", e.target.value)
                  }
                />
              </div>
              <div className="m-0 p-0">
                <p className="text-red-600 m-0 p-0 text-b">{error?.message}</p>
                {error?.errors?.map((errorLine: any, index: number) => (
                  <p key={index} className="text-red-600 m-0 p-0">
                    {errorLine}
                  </p>
                ))}
              </div>
            </div>

            <div className="!mt-4">
              <button
                type="submit"
                disabled={isLoading}
                className="w-full shadow-xl py-2.5 px-4 text-sm font-semibold rounded text-white bg-blue-900 hover:bg-blue-600 focus:outline-none disabled:bg-gray-500"
              >
                Register
              </button>
            </div>

            <div className="space-x-6 flex justify-center">
              <p className="text-sm mt-8 text-gray-800">
                If you already have an account{" "}
                <a className="text-blue-900 font-semibold hover:underline ml-1">
                  Login
                </a>
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
