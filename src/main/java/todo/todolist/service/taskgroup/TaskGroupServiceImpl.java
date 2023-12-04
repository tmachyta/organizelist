package todo.todolist.service.taskgroup;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import todo.todolist.dto.taskgroup.CreateTaskGroupRequest;
import todo.todolist.dto.taskgroup.TaskGroupDto;
import todo.todolist.exception.EntityNotFoundException;
import todo.todolist.mapper.taskgroup.TaskGroupMapper;
import todo.todolist.model.TaskGroup;
import todo.todolist.model.User;
import todo.todolist.repository.taskgroup.TaskGroupRepository;
import todo.todolist.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class TaskGroupServiceImpl implements TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;
    private final TaskGroupMapper taskGroupMapper;
    private final UserRepository userRepository;

    @Override
    public TaskGroupDto save(CreateTaskGroupRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find user by id " + request.getUserId()));

        TaskGroup taskGroup = taskGroupMapper.toModel(request);
        taskGroup.setUser(user);
        return taskGroupMapper.toDto(taskGroupRepository.save(taskGroup));
    }

    @Override
    public List<TaskGroupDto> getAll(Pageable pageable) {
        return taskGroupRepository.findAll(pageable)
                .stream()
                .map(taskGroupMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        taskGroupRepository.deleteById(id);
    }

    @Override
    public List<TaskGroupDto> getTaskGroupByUserId(Long userId) {
        return taskGroupRepository.findTaskGroupByUserId(userId)
                .stream()
                .map(taskGroupMapper::toDto)
                .toList();
    }

    @Override
    public void deleteByTaskGroupIdAndUserId(Long id, Long userId) {
        TaskGroup taskGroup = taskGroupRepository.findTaskGroupByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find task-group by id " + id + " and userId " + userId));

        taskGroupRepository.deleteById(taskGroup.getId());
    }

    @Override
    public List<TaskGroupDto> getTaskGroupByUserEmail(String email) {
        return taskGroupRepository.findTaskGroupByUserEmail(email)
                .stream()
                .map(taskGroupMapper::toDto)
                .toList();
    }
}
