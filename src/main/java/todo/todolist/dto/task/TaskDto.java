package todo.todolist.dto.task;

import java.time.LocalDate;
import lombok.Data;
import todo.todolist.model.Task;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private Task.Priority priority;
    private Task.Status status;
    private LocalDate date;
}
