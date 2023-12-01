package todo.todolist.service.role;

import todo.todolist.model.Role;
import todo.todolist.model.Role.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
