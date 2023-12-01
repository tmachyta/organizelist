package todo.todolist.service.task;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import todo.todolist.dto.task.CreateTaskRequest;
import todo.todolist.dto.task.TaskDto;
import todo.todolist.dto.task.UpdateTaskRequest;
import todo.todolist.exception.EntityNotFoundException;
import todo.todolist.mapper.task.TaskMapper;
import todo.todolist.model.Task;
import todo.todolist.model.TaskGroup;
import todo.todolist.model.User;
import todo.todolist.repository.task.TaskRepository;
import todo.todolist.repository.taskgroup.TaskGroupRepository;
import todo.todolist.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskGroupRepository taskGroupRepository;
    private final UserRepository userRepository;

    @Override
    public TaskDto save(CreateTaskRequest request) {

        TaskGroup taskGroup = taskGroupRepository.findById(request.getTaskGroupId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find task group by id " + request.getTaskGroupId()));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find user by id " + request.getUserId()));

        Task task = taskMapper.toModel(request);
        task.setTaskGroup(taskGroup);
        task.setUser(user);
        taskRepository.save(task);
        taskGroup.getTasks().add(task);
        taskGroupRepository.save(taskGroup);
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDto> getAll(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find task by id " + id));
        return taskMapper.toDto(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto update(Long id, UpdateTaskRequest request) {
        Task existedTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find task by id " + id));
        existedTask.setName(request.getName());
        existedTask.setDescription(request.getDescription());
        existedTask.setPriority(request.getPriority());
        existedTask.setStatus(request.getStatus());
        existedTask.setDate(request.getDate());
        return taskMapper.toDto(taskRepository.save(existedTask));
    }

    @Override
    public List<TaskDto> getTaskByUserId(Long userId) {
        return taskRepository.findUserById(userId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public TaskDto findTaskByIdAndUserId(Long id, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find task by id " + id + " and userId " + userId));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto updateByIdAndUserId(Long id, UpdateTaskRequest request, Long userId) {
        Task existedTask = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find task by id " + id + " and userId " + userId));
        existedTask.setName(request.getName());
        existedTask.setDescription(request.getDescription());
        existedTask.setPriority(request.getPriority());
        existedTask.setStatus(request.getStatus());
        existedTask.setDate(request.getDate());
        return taskMapper.toDto(taskRepository.save(existedTask));
    }

    @Override
    public void deleteByIdAndUserId(Long id, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find task by id " + id + " and userId " + userId));
        taskRepository.deleteById(task.getId());
    }
}
