package com.project.personaltaskmanager.service;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.model.Task;
import java.util.List;

/**
 * Defines business operations for tasks.
 */
public interface TaskService {

    /**
     * Retrieve all tasks, optionally filtered or sorted by repository methods.
     */
    List<Task> getAllTasks();

    /**
     * Find a single task by ID.
     * @throws ResourceNotFoundException if not found.
     */
    Task getTaskById(Long id);

    /**
     * Create a new task from the provided DTO.
     * @throws InvalidDataException on business-rule violation.
     */
    Task createTask(TaskDTO dto);

    /**
     * Update an existing task using data from the DTO.
     * @throws ResourceNotFoundException if target not found.
     * @throws InvalidDataException on business-rule violation.
     */
    Task updateTask(Long id, TaskDTO dto);

    /**
     * Delete a task by its ID.
     * @throws ResourceNotFoundException if not found.
     */
    void deleteTask(Long id);
}
