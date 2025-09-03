package com.project.personaltaskmanager.service.impl;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.exception.InvalidDataException;
import com.project.personaltaskmanager.exception.ResourceNotFoundException;
import com.project.personaltaskmanager.model.Category;
import com.project.personaltaskmanager.model.Priority;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.repository.CategoryRepository;
import com.project.personaltaskmanager.repository.PriorityRepository;
import com.project.personaltaskmanager.repository.TaskRepository;
import com.project.personaltaskmanager.service.TaskService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of TaskService, handling
 * business logic and entity mapping.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;
    private final CategoryRepository categoryRepo;
    private final PriorityRepository priorityRepo;

    public TaskServiceImpl(TaskRepository taskRepo,
                           CategoryRepository categoryRepo,
                           PriorityRepository priorityRepo) {
        this.taskRepo = taskRepo;
        this.categoryRepo = categoryRepo;
        this.priorityRepo = priorityRepo;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id " + id));
    }

    @Override
    public Task createTask(TaskDTO dto) {
        validateBusinessRules(dto);
        Category category = categoryRepo.findById(dto.getCategoryId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Category not found with id " + dto.getCategoryId()));
        Priority priority = priorityRepo.findById(dto.getPriorityId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Priority not found with id " + dto.getPriorityId()));

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setCategory(category);
        task.setPriority(priority);
        task.setCompleted(dto.isCompleted());

        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskDTO dto) {
        validateBusinessRules(dto);
        Task existing = getTaskById(id);

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setDueDate(dto.getDueDate());

        Category category = categoryRepo.findById(dto.getCategoryId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Category not found with id " + dto.getCategoryId()));
        existing.setCategory(category);

        Priority priority = priorityRepo.findById(dto.getPriorityId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Priority not found with id " + dto.getPriorityId()));
        existing.setPriority(priority);

        existing.setCompleted(dto.isCompleted());
        return taskRepo.save(existing);
    }

    @Override
    public void deleteTask(Long id) {
        Task toDelete = getTaskById(id);
        taskRepo.delete(toDelete);
    }

    /**
     * Enforce business-rule: due date must not be in the past.
     */
    private void validateBusinessRules(TaskDTO dto) {
        LocalDate due = dto.getDueDate();
        if (due != null && due.isBefore(LocalDate.now())) {
            throw new InvalidDataException("Due date cannot be in the past");
        }
    }
}
