package todo.todolist.repository.taskgroup;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import todo.todolist.model.TaskGroup;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {

    @Query("SELECT DISTINCT tg FROM TaskGroup tg JOIN tg.tasks t WHERE t.user.id = :userId")
    List<TaskGroup> findTaskGroupByUserId(@Param("userId") Long userId);

    @Query("SELECT DISTINCT tg FROM TaskGroup tg "
            + "JOIN tg.tasks t "
            + "WHERE t.id = :id AND t.user.id = :userId")
    Optional<TaskGroup> findTaskGroupByIdAndUserId(@Param("id") Long id,
                                                   @Param("userId") Long userId);

    @Query("SELECT DISTINCT tg FROM TaskGroup tg JOIN tg.tasks t WHERE t.user.email = :email")
    List<TaskGroup> findTaskGroupByUserEmail(@Param("email") String email);
}
