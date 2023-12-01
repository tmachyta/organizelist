package todo.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import todo.todolist.dto.task.CreateTaskRequest;
import todo.todolist.dto.task.TaskDto;
import todo.todolist.dto.task.UpdateTaskRequest;
import todo.todolist.service.task.TaskService;

@Tag(name = "Task management", description = "Endpoints for managing tasks")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/tasks")
public class TaskController {
    private final TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all tasks", description = "Get a list of all available tasks")
    public List<TaskDto> findAll(@ParameterObject Pageable pageable) {
        return taskService.getAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    @Operation(summary = "Save task to repository", description = "Save valid task to repository")
    public TaskDto save(@RequestBody @Valid CreateTaskRequest request) {
        return taskService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get task by id", description = "Get available task by id")
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id", description = "Soft delete of available task by id")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update task by id", description = "Update available task by id")
    public TaskDto update(@PathVariable Long id,
                          @RequestBody @Valid UpdateTaskRequest request) {
        return taskService.update(id, request);
    }

    @GetMapping("/user")
    @PreAuthorize("#userId == authentication.principal.id")
    @Operation(summary = "Get all tasks",
            description = "Get a list of all available tasks by authenticated user")
    public List<TaskDto> getTaskByUserId(@RequestParam Long userId) {
        return taskService.getTaskByUserId(userId);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("#userId == authentication.principal.id")
    @Operation(summary = "Get task by id",
            description = "Get available task by id by authenticated user")
    public TaskDto getTaskByIdAndUserId(@PathVariable Long id,
                                        @RequestParam Long userId) {
        return taskService.findTaskByIdAndUserId(id, userId);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("#userId == authentication.principal.id")
    @Operation(summary = "Update task by id",
            description = "Update available task by id by authenticated user")
    public TaskDto updateTaskByIdAndUserId(@PathVariable Long id,
                          @RequestBody @Valid UpdateTaskRequest request,
                                           @RequestParam Long userId) {
        return taskService.updateByIdAndUserId(id, request, userId);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("#userId == authentication.principal.id")
    @Operation(summary = "Delete task by id by user",
            description = "Soft delete of available task by id by authenticated user")
    public void deleteByTaskIdAndUserId(@PathVariable Long id,
                                        @RequestParam Long userId) {
        taskService.deleteByIdAndUserId(id, userId);
    }
}
