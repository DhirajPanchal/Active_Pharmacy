import React from "react";
import { IUser } from "../model/auth.model";

type ProfileProps = {
  pricipal?: IUser;
};

export default function Profile({ pricipal }: ProfileProps) {
  function renderAddress(pricipal: IUser): React.ReactNode {
    let content: any;
    if (pricipal && pricipal.address) {
      //content = {pricipal.address.address1 } <br /> {pricipal.address.address2}
    }

    return content;
  }

  // content += pricipal.address.address1 + <br />;
  // content += pricipal.address.address2 + <br />;
  // content += pricipal.address.city + <br />;
  // content += pricipal.address.state + <br />;
  // content += pricipal.address.country + <br />;
  // content += pricipal.address.zipCode + <br />;

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
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.firstName} {pricipal?.lastName} ( {pricipal?.role} )
            </dd>
          </div>
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Email address</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.email}
            </dd>
          </div>
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Phone number</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.phoneNumber}
            </dd>
          </div>
          <div className="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
            <dt className="text-sm font-medium text-gray-500">Address</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
              {pricipal?.address?.address1}
              <br/>
              {pricipal?.address?.address2}
              <br/>
              {pricipal?.address?.city}, {pricipal?.address?.state}
              <br/>
              {pricipal?.address?.country} {pricipal?.address?.zipCode}
            </dd>
          </div>
        </dl>
      </div>
    </div>
  );
}