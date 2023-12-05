export enum RoleName {
  ADMIN = 'ADMIN',
  USER = 'USER'
}

export class Role {
  id: number = 0;
  roleName: RoleName = RoleName.USER;
}
