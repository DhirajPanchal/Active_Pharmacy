import React, { useEffect } from "react";
import { shallowEqual } from "react-redux";
import { appSelector, useThunk } from "../store/hooks";
import { userProfile } from "../store/store";

export default function Profile() {
  const [doProfile, isLoading, error] = useThunk(userProfile);

  const [isAuthenticated, pricipal] = appSelector((state) => {
    return [state.auth.isAuthenticated, state.auth.pricipal];
  }, shallowEqual);

  useEffect(() => {
    console.log("< Profile >");

    // if (isAuthenticated) {
    doProfile();
    // }
  }, []);

  return (
    <div className="bg-white overflow-hidden shadow rounded-lg border m-8 ">
      <div className="px-4 py-5 sm:px-6">
        <h3 className="text-lg leading-6 font-medium text-gray-900">
          User Profile
        </h3>
      </div>
      <div className="border-t border-gray-200 px-4 py-5 sm:p-0">
        <dl className="sm:divide-y sm:divide-gray-200">
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Full name</dt>
            <dd className="mt-1 text-lg text-blue-900 sm:mt-0 sm:col-span-2">
              {pricipal?.firstName} {pricipal?.lastName}
            </dd>
          </div>
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Email address</dt>
            <dd className="mt-1 text-lg text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.email}
            </dd>
          </div>
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Phone number</dt>
            <dd className="mt-1 text-lg text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.phoneNumber}
            </dd>
          </div>
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Address</dt>
            <dd className="mt-1 text-lg text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.address?.address1}
              <br />
              {pricipal?.address?.address2}
              <br />
              {pricipal?.address?.city}, {pricipal?.address?.state}
              <br />
              {pricipal?.address?.country} {pricipal?.address?.zipCode}
            </dd>
          </div>

          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Role</dt>
            <dd className="mt-1 text-lg text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.role}
            </dd>
          </div>
        </dl>
      </div>
    </div>
  );
}
