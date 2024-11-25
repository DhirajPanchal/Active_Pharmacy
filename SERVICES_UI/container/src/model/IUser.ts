export interface IUser {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  business?:boolean
  roles: string[];
}
