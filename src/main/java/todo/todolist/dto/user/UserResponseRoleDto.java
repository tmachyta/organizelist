package todo.todolist.dto.user;

import java.util.Set;
import lombok.Data;
import todo.todolist.model.Role;

@Data
public class UserResponseRoleDto {
    private Set<Role> roles;
}
