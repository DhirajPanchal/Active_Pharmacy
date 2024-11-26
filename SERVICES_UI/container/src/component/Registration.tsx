import React, { useState } from "react";
import ExternalInterface from "../service/ExternalInterface";
import { useNavigate } from "react-router-dom";
import { IRegistration } from "../model/auth.model";

const defaultFormData: IRegistration = {
  email: "",
  firstName: "",
  lastName: "",
  password: "",
  matchingPassword: "",
  ui_only_errors: [],
};

export default function Registration() {
  const [formData, setFormData] = useState<IRegistration>(defaultFormData);

  const navigation = useNavigate();

  const formSubmit = (event: any) => {
    event.preventDefault();
    console.log(formData);
    // if (formData?.password !== formData?.matchingPassword) {
    //   return;
    // }

    const payload: IRegistration = { ...formData };
    payload.firstName = payload.firstName.trim();
    payload.lastName = payload.lastName.trim();
    payload.password = payload.password.trim();
    payload.matchingPassword = payload.matchingPassword.trim();
    ExternalInterface.userRegistration(payload)
      .then((data) => {
        console.log("DATA  :: ");
        console.log(data);
        navigation("/login");
      })
      .catch((error: any) => {
        console.log("ERROR  :: ");
        console.log(error);
        const message = error.response?.data?.message;
        const errors = error.response?.data?.errors;
        console.log("----");
        console.log(errors);

        // if (errors && errors?.length > 0) {
        setFormData((pre) => {
          console.log(errors);
          return { ...pre, ui_only_errors: errors, ui_only_message: message };
        });
        // }
      });
  };

  const updateValue = (key: string, value: any) => {
    console.log(`-------------------------------${key} :: ${value}`);
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

  console.log(" FORM :: ", formData.ui_only_message);

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
                <p className="text-red-600 m-0 p-0">
                  {formData.ui_only_message}
                </p>
                {formData.ui_only_errors?.map((err, index) => (
                  <p key={index} className="text-red-600 m-0 p-0">
                    {err}
                  </p>
                ))}
              </div>
            </div>

            <div className="!mt-4">
              <button
                type="submit"
                className="w-full shadow-xl py-2.5 px-4 text-sm font-semibold rounded text-white bg-blue-900 hover:bg-blue-600 focus:outline-none"
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
