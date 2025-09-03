package com.project.personaltaskmanager.repository;

import com.project.personaltaskmanager.model.entity.Category;
import com.project.personaltaskmanager.model.entity.Task;
import com.project.personaltaskmanager.model.enums.Priority;
import com.project.personaltaskmanager.model.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.active = true " +
            "ORDER BY " +
            "CASE t.priority WHEN 'URGENT' THEN 4 WHEN 'HIGH' THEN 3 WHEN 'MEDIUM' THEN 2 WHEN 'LOW' THEN 1 END DESC, "
            +
            "t.dueDate ASC NULLS LAST, t.createdAt DESC")
    List<Task> findAllActiveOrderedByPriorityAndDueDate();

    Page<Task> findByActiveTrueOrderByCreatedAtDesc(Pageable pageable);

    List<Task> findByStatusAndActiveTrueOrderByCreatedAtDesc(TaskStatus status);

    Page<Task> findByStatusAndActiveTrueOrderByCreatedAtDesc(TaskStatus status, Pageable pageable);

    long countByStatus(TaskStatus status);

    long countByStatusAndActiveTrue(TaskStatus status);

    List<Task> findByPriorityAndActiveTrueOrderByDueDateAsc(Priority priority);

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.priority IN ('HIGH', 'URGENT') AND " +
            "t.status NOT IN ('COMPLETED', 'CANCELLED') " +
            "ORDER BY t.priority DESC, t.dueDate ASC NULLS LAST")
    List<Task> findHighPriorityTasks();

    long countByPriorityAndActiveTrue(Priority priority);

    List<Task> findByCategoryAndActiveTrueOrderByCreatedAtDesc(Category category);

    Page<Task> findByCategoryAndActiveTrueOrderByCreatedAtDesc(Category category, Pageable pageable);

    List<Task> findByCategoryIdAndActiveTrueOrderByCreatedAtDesc(Long categoryId);

    long countByCategoryAndActiveTrue(Category category);

    List<Task> findByDueDateAndActiveTrueOrderByPriorityDesc(LocalDate dueDate);

    @Query("SELECT t FROM Task t WHERE t.active = true AND t.dueDate = CURRENT_DATE " +
            "ORDER BY t.priority DESC, t.createdAt ASC")
    List<Task> findTasksDueToday();

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.dueDate < CURRENT_DATE AND " +
            "t.status NOT IN ('COMPLETED', 'CANCELLED') " +
            "ORDER BY t.dueDate ASC, t.priority DESC")
    List<Task> findOverdueTasks();

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.dueDate BETWEEN CURRENT_DATE AND :endDate AND " +
            "t.status NOT IN ('COMPLETED', 'CANCELLED') " +
            "ORDER BY t.dueDate ASC, t.priority DESC")
    List<Task> findTasksDueWithinDays(@Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.dueDate BETWEEN CURRENT_DATE AND :endOfWeek AND " +
            "t.status NOT IN ('COMPLETED', 'CANCELLED') " +
            "ORDER BY t.dueDate ASC, t.priority DESC")
    List<Task> findTasksDueThisWeek(@Param("endOfWeek") LocalDate endOfWeek);

    List<Task> findByDueDateIsNullAndActiveTrueOrderByPriorityDescCreatedAtDesc();

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
            "ORDER BY t.priority DESC, t.dueDate ASC NULLS LAST")
    List<Task> findByTitleOrDescriptionContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "(:searchTerm IS NULL OR " +
            " LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            " LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority) AND " +
            "(:categoryId IS NULL OR t.category.id = :categoryId)")
    Page<Task> findTasksWithFilters(@Param("searchTerm") String searchTerm,
            @Param("status") TaskStatus status,
            @Param("priority") Priority priority,
            @Param("categoryId") Long categoryId,
            Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.createdAt >= :startDate " +
            "ORDER BY t.createdAt DESC")
    List<Task> findRecentTasks(@Param("startDate") LocalDate startDate);

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.status = 'COMPLETED' AND " +
            "t.completedAt >= :startDateTime " +
            "ORDER BY t.completedAt DESC")
    List<Task> findRecentlyCompletedTasks(@Param("startDateTime") LocalDate startDateTime);

    @Query("SELECT " +
            "COUNT(CASE WHEN t.status = 'PENDING' THEN 1 END) as pending, " +
            "COUNT(CASE WHEN t.status = 'IN_PROGRESS' THEN 1 END) as inProgress, " +
            "COUNT(CASE WHEN t.status = 'COMPLETED' THEN 1 END) as completed, " +
            "COUNT(CASE WHEN t.status = 'CANCELLED' THEN 1 END) as cancelled, " +
            "COUNT(CASE WHEN t.dueDate < CURRENT_DATE AND t.status NOT IN ('COMPLETED', 'CANCELLED') THEN 1 END) as overdue "
            +
            "FROM Task t WHERE t.active = true")
    Object[] getTaskStatistics();

    List<Task> findByStatusInAndActiveTrueOrderByPriorityDescDueDateAsc(List<TaskStatus> statuses);

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.status IN ('PENDING', 'IN_PROGRESS') " +
            "ORDER BY t.priority DESC, t.dueDate ASC NULLS LAST")
    List<Task> findIncompleteTasks();

    @Query("SELECT t FROM Task t WHERE t.active = true AND " +
            "t.status = 'COMPLETED' AND " +
            "DATE(t.completedAt) BETWEEN :startDate AND :endDate " +
            "ORDER BY t.completedAt DESC")
    List<Task> findCompletedTasksBetweenDates(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}