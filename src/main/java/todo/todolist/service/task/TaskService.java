package todo.todolist.service.task;

import java.util.List;
import org.springframework.data.domain.Pageable;
import todo.todolist.dto.task.CreateTaskRequest;
import todo.todolist.dto.task.TaskDto;
import todo.todolist.dto.task.UpdateTaskRequest;

public interface TaskService {

    TaskDto save(CreateTaskRequest request);

    List<TaskDto> getAll(Pageable pageable);

    TaskDto findById(Long id);

    void deleteById(Long id);

    TaskDto update(Long id, UpdateTaskRequest request);

    List<TaskDto> getTaskByUserId(Long userId);

    TaskDto findTaskByIdAndUserId(Long id, Long userId);

    TaskDto updateByIdAndUserId(Long id, UpdateTaskRequest request, Long userId);

    void deleteByIdAndUserId(Long id, Long userId);
}
