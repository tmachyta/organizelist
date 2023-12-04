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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import todo.todolist.dto.taskgroup.CreateTaskGroupRequest;
import todo.todolist.dto.taskgroup.TaskGroupDto;
import todo.todolist.service.taskgroup.TaskGroupService;

@Tag(name = "TaskGroup management", description = "Endpoints for managing task-groups")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/task-groups")
public class TaskGroupController {
    private final TaskGroupService taskGroupService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all tasks-groups",
            description = "Get a list of all available task-groups")
    public List<TaskGroupDto> findAll(@ParameterObject Pageable pageable) {
        return taskGroupService.getAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    @Operation(summary = "Save task group to repository",
            description = "Save valid group to repository")
    public TaskGroupDto save(@RequestBody @Valid CreateTaskGroupRequest request) {
        return taskGroupService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task-group by id",
            description = "Soft delete of available task-group by id")
    public void deleteById(@PathVariable Long id) {
        taskGroupService.deleteById(id);
    }

    @GetMapping("/user/me")
    @PreAuthorize("#userId == authentication.principal.id")
    @Operation(summary = "Get all tasks-groups",
            description = "Get a list of all available task-groups by authenticated user")
    public List<TaskGroupDto> getTaskGroupByUserId(@RequestParam Long userId) {
        return taskGroupService.getTaskGroupByUserId(userId);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("#userId == authentication.principal.id")
    @Operation(summary = "Delete task-group by id by user",
            description = "Soft delete of available task by id by authenticated user")
    public void deleteByTaskGroupIdAndUserId(@PathVariable Long id,
                                        @RequestParam Long userId) {
        taskGroupService.deleteByTaskGroupIdAndUserId(id, userId);
    }

    @GetMapping("/user")
    @PreAuthorize("#email == authentication.principal.email")
    @Operation(summary = "Get all tasks-groups",
            description = "Get a list of all available task-groups by authenticated user")
    public List<TaskGroupDto> getTaskGroupByUserEmail(@RequestParam String email) {
        return taskGroupService.getTaskGroupByUserEmail(email);
    }
}
