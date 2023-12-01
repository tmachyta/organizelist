package todo.todolist.mapper.task;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import todo.todolist.config.MapperConfig;
import todo.todolist.dto.task.CreateTaskRequest;
import todo.todolist.dto.task.TaskDto;
import todo.todolist.model.Task;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {
    TaskDto toDto(Task task);

    @Mapping(target = "id", ignore = true)
    Task toModel(CreateTaskRequest request);
}
