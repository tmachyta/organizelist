package todo.todolist.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import todo.todolist.config.MapperConfig;
import todo.todolist.dto.user.UserRegistrationRequest;
import todo.todolist.dto.user.UserResponseDto;
import todo.todolist.dto.user.UserResponseRoleDto;
import todo.todolist.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toModel(UserRegistrationRequest request);

    UserResponseDto toUserResponse(User user);

    UserResponseRoleDto toUserRoleResponse(User user);
}
