package todo.todolist.dto.taskgroup;

import lombok.Data;

@Data
public class CreateTaskGroupRequest {
    private String name;
    // private Set<TaskDto> tasks;
    private Long userId;
}
