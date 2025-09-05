package com.project.personaltaskmanager.service;

import java.util.List;
import com.project.personaltaskmanager.dto.TaskDto;

public interface TaskService {
    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Long id);

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);

    List<TaskDto> getTasksByCategory(Long categoryId);

    List<TaskDto> getTasksByStatus(String status);
}