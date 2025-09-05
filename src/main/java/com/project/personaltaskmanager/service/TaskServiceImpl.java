package com.project.personaltaskmanager.service;

import org.springframework.stereotype.Service;
import com.project.personaltaskmanager.dto.TaskDto;
import com.project.personaltaskmanager.entities.Category;
import com.project.personaltaskmanager.entities.Status;
import com.project.personaltaskmanager.entities.Task;
import com.project.personaltaskmanager.exception.ResourceNotFoundException;
import com.project.personaltaskmanager.mapper.TaskMapper;
import com.project.personaltaskmanager.repository.CategoryRepository;
import com.project.personaltaskmanager.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskServiceImpl(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Category category = categoryRepository.findById(taskDto.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category not found with id " + taskDto.getCategoryId()));
        Task task = TaskMapper.toEntity(taskDto, category);
        Task saved = taskRepository.save(task);
        return TaskMapper.toDto(saved);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        Category category = categoryRepository.findById(taskDto.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category not found with id " + taskDto.getCategoryId()));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCategory(category);
        task.setPriority(Enum.valueOf(com.project.personaltaskmanager.entities.Priority.class, taskDto.getPriority()));
        task.setStatus(Enum.valueOf(com.project.personaltaskmanager.entities.Status.class, taskDto.getStatus()));
        task.setDueDate(taskDto.getDueDate());
        Task updated = taskRepository.save(task);
        return TaskMapper.toDto(updated);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getTasksByCategory(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId)
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByStatus(String status) {
        return taskRepository.findByStatus(Status.valueOf(status))
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }
}