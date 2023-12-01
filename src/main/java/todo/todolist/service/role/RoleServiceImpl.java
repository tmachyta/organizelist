package todo.todolist.service.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.todolist.exception.EntityNotFoundException;
import todo.todolist.model.Role;
import todo.todolist.repository.role.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByRoleName(Role.RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find role by roleName " + roleName));
    }
}
