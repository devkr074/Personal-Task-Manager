package com.project.personaltaskmanager.repository;
import com.project.personaltaskmanager.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Filter by completion status
    List<Task> findByCompleted(boolean completed);

    // Filter by category name
    List<Task> findByCategoryName(String categoryName);

    // Filter by priority level
    List<Task> findByPriorityLevel(String level);

    // Search by title keyword (case-insensitive)
    List<Task> findByTitleContainingIgnoreCase(String keyword);

    // Sort by due date
    List<Task> findAllByOrderByDueDateAsc();
    List<Task> findAllByOrderByDueDateDesc();

    // Sort by priority level
    List<Task> findAllByOrderByPriorityLevelAsc();
    List<Task> findAllByOrderByPriorityLevelDesc();
}
