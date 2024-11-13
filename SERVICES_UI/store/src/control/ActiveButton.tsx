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
  const classes = classnames(props.className, "flex items-center px-3 py-2 min-w-12  border-2 shadow-xl hover:bg-sky-100 ", {
    "border-blue-200 bg-blue-100 text-gray": primary,
    "border-gray-200 bg-gray-100 text-gray": secondary,
    "border-green-200 bg-green-100 text-gray": success,
    "border-yellow-200 bg-yellow-100 text-gray": warning,
    "border-red-200 bg-red-100 text-gray": danger,
    "rounded-xl": rounded,
    "bg-white": outline,
    "text-gray-900": outline && primary,
    "text-gray-904": outline && secondary,
    "text-gray-901": outline && success,
    "text-gray-902": outline && warning,
    "text-gray-903": outline && danger,

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
