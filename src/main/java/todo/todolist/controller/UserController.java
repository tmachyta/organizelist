package todo.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todolist.dto.user.UserResponseDto;
import todo.todolist.service.user.UserService;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/me/{id}")
    @Operation(summary = "Get task by id", description = "Get available task by id")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
