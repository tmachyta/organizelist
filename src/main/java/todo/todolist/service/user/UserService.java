package todo.todolist.service.user;

import todo.todolist.dto.user.UserRegistrationRequest;
import todo.todolist.dto.user.UserResponseDto;
import todo.todolist.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequest request) throws RegistrationException;

    UserResponseDto findById(Long id);
}
