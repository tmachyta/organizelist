import {RoleName} from "../role/role";

export class UserRegistrationRequest {
  email: string = '' ;
  password: string = '';
  repeatPassword: string = '';
  firstName: string = '';
  lastName: string = '';
  phone: string = '';
  role: RoleName = RoleName.USER;
}
