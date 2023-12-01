package todo.todolist.dto.taskgroup;

import java.util.Set;
import lombok.Data;
import todo.todolist.dto.task.TaskDto;

@Data
public class CreateTaskGroupRequest {
    private String name;
    private Set<TaskDto> tasks;
    private Long userId;
}
