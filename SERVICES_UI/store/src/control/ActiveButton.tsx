import classnames from "classnames";
import React, { ComponentPropsWithoutRef, PropsWithChildren } from "react";
import { ReactNode } from "react";

type ActiveButtonProps = {
  children?: ReactNode;
  primary?: boolean;
  secondary?: boolean;
  success?: boolean;
  warning?: boolean;
  danger?: boolean;
  outline?: boolean;
  rounded?: boolean;
};

function ActiveButton({
  children,
  primary,
  secondary,
  success,
  warning,
  danger,
  outline,
  rounded,
  ...props
}: PropsWithChildren<ActiveButtonProps> & ComponentPropsWithoutRef<"button">) {
  const classes = classnames(props.className, "flex items-center px-3 py-1.5 border-2 shadow-xl hover:bg-sky-50 ", {
    "border-blue-500 bg-blue-500 text-white": primary,
    "border-gray-900 bg-gray-900 text-white": secondary,
    "border-green-500 bg-green-500 text-white": success,
    "border-yellow-400 bg-yellow-400 text-white": warning,
    "border-red-500 bg-red-500 text-white": danger,
    "rounded-full": rounded,
    "bg-white": outline,
    "text-blue-900": outline && primary,
    "text-gray-900": outline && secondary,
    "text-green-500": outline && success,
    "text-yellow-400": outline && warning,
    "text-red-500": outline && danger,

  });

  return (
    <button {...props} className={classes}>
      {children}
    </button>
  );
}

ActiveButton.propTypes = {
  checkVariationValue: ({
    primary,
    secondary,
    success,
    warning,
    danger,
  }: ActiveButtonProps) => {
    const count =
      Number(!!primary) +
      Number(!!secondary) +
      Number(!!warning) +
      Number(!!success) +
      Number(!!danger);

    if (count > 1) {
      return new Error(
        "Only one of primary, secondary, success, warning, danger can be true"
      );
    }
  },
};

export default ActiveButton;
