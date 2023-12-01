package todo.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todolist.dto.user.UserLoginRequest;
import todo.todolist.dto.user.UserLoginResponse;
import todo.todolist.dto.user.UserRegistrationRequest;
import todo.todolist.dto.user.UserResponseDto;
import todo.todolist.exception.RegistrationException;
import todo.todolist.security.AuthenticationService;
import todo.todolist.service.user.UserService;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "Login", description = "Login method to authenticate users")
    public UserLoginResponse login(@RequestBody UserLoginRequest requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    @PermitAll
    @Operation(summary = "Register", description = "Register method to register users")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequest request)
            throws RegistrationException {
        return userService.register(request);
    }
}
