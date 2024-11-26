interface IRegistration {
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  matchingPassword: string;

  ui_only_message?: string;
  ui_only_errors?: string[];
}

interface ILogin {
  username: string;
  password: string;

  ui_only_message?: string;
  ui_only_errors?: string[];
}

interface IUser {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  role: string;
  phoneNumber: string;
  address: IAddress;

  ui_only_message?: string;
  ui_only_errors?: string[];
}

interface IAddress {
  id: number;
  address1: string;
  address2: string;
  city: string;
  state: string;
  country: string;
  zipCode: string;
}

export type { IRegistration, ILogin, IUser, IAddress };
