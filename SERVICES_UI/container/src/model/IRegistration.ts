interface IRegistration {
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  matchingPassword: string;
  ui_only_message?:string;
  ui_only_errors?:string[];
}
