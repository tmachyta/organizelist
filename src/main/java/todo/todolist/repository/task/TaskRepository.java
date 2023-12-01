package todo.todolist.repository.task;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import todo.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId")
    List<Task> findUserById(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.user.id = :userId")
    Optional<Task> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
}
