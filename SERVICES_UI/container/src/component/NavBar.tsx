import React, { useState } from "react";

type OptionType = { label: string; action: string };

const options: OptionType[] = [
  //   { label: "Login", action: "login" },
  //   { label: "Logout", action: "logout" },
  //   { label: "Profile", action: "profile" },
];

export default function NavBar() {
  const [openMenu, setOpenMenu] = useState<boolean>(true);

  const handleSearch = () => {
    console.log("_handleSearch");
  };

  const handleMenu = () => {
    console.log("_handleMenu");
    setOpenMenu((pre) => !pre);
  };

  const optionHandle = (option: OptionType) => {
    console.log("_optionHandle : " + option.action);
    setOpenMenu((pre) => !pre);
  };

  return (
    <nav className=" bg-white w-full flex relative justify-between items-center mx-auto px-8 h-20">
      {/* Logo */}

      <div className="inline-flex">
        <a className="_o6689fn" href="/">
          <div className="hidden md:block">
            <img src="/activeRx_Pharmacy_c02.png" alt="Logo" />
          </div>
          <div className="block md:hidden">
            <img src="/activeRx_Pharmacy_c02.png" alt="Logo" />
          </div>
        </a>
      </div>

      {/* Logo end */}

      {/* Search Bar  */}

      <div className="hidden sm:block flex-shrink flex-grow-0 justify-start px-2 w-80">
        <div className="inline-block w-full ">
          <div className="inline-flex items-center w-full max-w-full  ">
            <button
              className="flex items-center flex-grow-0 flex-shrink pl-2 relative w-full border-2 rounded-full px-1  py-1"
              type="button"
              onClick={handleSearch}
            >
              <input
                type="text"
                placeholder="search drug"
                className="border-none w-full h-fit"
              />
              <div className="flex items-center justify-center relative  h-8 w-8 rounded-full">
                <svg
                  viewBox="0 0 32 32"
                  xmlns="http://www.w3.org/2000/svg"
                  aria-hidden="true"
                  role="presentation"
                  focusable="false"
                  className="block fill-none h-18 w-12 stroke-current overflow-clip"
                >
                  <g fill="none">
                    <path d="m13 24c6.0751322 0 11-4.9248678 11-11 0-6.07513225-4.9248678-11-11-11-6.07513225 0-11 4.92486775-11 11 0 6.0751322 4.92486775 11 11 11zm8-3 9 9"></path>
                  </g>
                </svg>
              </div>
            </button>
          </div>
        </div>
      </div>

      {/* Search Bar end */}

      {/* Login */}

      <div className="flex-initial">
        <div className="flex justify-end items-center relative">
          <div className="flex mr-4 items-center">
            {/* <a
              className="inline-block py-2 px-3 hover:bg-gray-200 rounded-full"
              href="#"
            >
              LINK
            </a> */}
          </div>

          <div className="block w-32 ">
            <div className="inline relative float-end">
              <button
                type="button"
                className="inline-flex items-center relative px-2 rounded-full hover:shadow-lg hover:bg-green-100 border-2"
                onClick={handleMenu}
              >
                <div className="pl-1">
                  <svg
                    viewBox="0 0 32 32"
                    xmlns="http://www.w3.org/2000/svg"
                    aria-hidden="true"
                    role="presentation"
                    focusable="false"
                    className="block fill-none h-8 w-8 stroke-current overflow-visible"
                  >
                    <g fill="none" fillRule="nonzero">
                      <path d="m2 16h28"></path>
                      <path d="m2 24h28"></path>
                      <path d="m2 8h28"></path>
                    </g>
                  </svg>
                </div>

                <div className="block flex-grow-0 flex-shrink-0 h-10 w-12 pl-4 pt-1">
                  <svg
                    viewBox="0 0 32 32"
                    xmlns="http://www.w3.org/2000/svg"
                    aria-hidden="true"
                    role="presentation"
                    focusable="false"
                    className="block h-8 w-8 fill-current"
                  >
                    <path d="m16 .7c-8.437 0-15.3 6.863-15.3 15.3s6.863 15.3 15.3 15.3 15.3-6.863 15.3-15.3-6.863-15.3-15.3-15.3zm0 28c-4.021 0-7.605-1.884-9.933-4.81a12.425 12.425 0 0 1 6.451-4.4 6.507 6.507 0 0 1 -3.018-5.49c0-3.584 2.916-6.5 6.5-6.5s6.5 2.916 6.5 6.5a6.513 6.513 0 0 1 -3.019 5.491 12.42 12.42 0 0 1 6.452 4.4c-2.328 2.925-5.912 4.809-9.933 4.809z"></path>
                  </svg>
                </div>
              </button>
            </div>

            {openMenu && (
              <ul className="absolute z-[1000] right-0 top-12 m-0 list-none overflow-hidden rounded-lg border-none bg-clip-padding text-center text-base shadow-2xl data-[twe-dropdown-show]:block dark:bg-surface-dark w-32 min-w-max bg-green-100">
                {options.map((option, index) => (
                  <li key={index}>
                    <a
                      className="block w-full whitespace-nowrap bg-white px-4 py-2 text-sm font-normal text-neutral-700 hover:bg-zinc-200/60 focus:bg-zinc-200/60 focus:outline-none active:bg-zinc-200/60 active:no-underline dark:bg-surface-dark dark:text-white dark:hover:bg-neutral-800/25 dark:focus:bg-neutral-800/25 dark:active:bg-neutral-800/25"
                      onClick={() => optionHandle(option)}
                    >
                      {option.label}
                    </a>
                  </li>
                ))}
              </ul>
            )}
          </div>
        </div>
      </div>

      {/* Login end */}
    </nav>
  );
}
