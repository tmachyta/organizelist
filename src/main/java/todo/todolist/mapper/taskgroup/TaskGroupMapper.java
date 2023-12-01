package todo.todolist.mapper.taskgroup;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import todo.todolist.config.MapperConfig;
import todo.todolist.dto.taskgroup.CreateTaskGroupRequest;
import todo.todolist.dto.taskgroup.TaskGroupDto;
import todo.todolist.model.TaskGroup;

@Mapper(config = MapperConfig.class)
public interface TaskGroupMapper {
    TaskGroupDto toDto(TaskGroup taskGroup);

    @Mapping(target = "id", ignore = true)
    TaskGroup toModel(CreateTaskGroupRequest request);
}
