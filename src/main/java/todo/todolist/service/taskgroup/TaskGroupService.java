package todo.todolist.service.taskgroup;

import java.util.List;
import org.springframework.data.domain.Pageable;
import todo.todolist.dto.taskgroup.CreateTaskGroupRequest;
import todo.todolist.dto.taskgroup.TaskGroupDto;

public interface TaskGroupService {
    TaskGroupDto save(CreateTaskGroupRequest request);

    List<TaskGroupDto> getAll(Pageable pageable);

    void deleteById(Long id);

    List<TaskGroupDto> getTaskGroupByUserId(Long userId);

    void deleteByTaskGroupIdAndUserId(Long id, Long userId);
}
