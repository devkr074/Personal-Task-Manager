package com.project.personaltaskmanager.mapper;

import com.project.personaltaskmanager.dto.TaskDto;
import com.project.personaltaskmanager.entities.Category;
import com.project.personaltaskmanager.entities.Priority;
import com.project.personaltaskmanager.entities.Status;
import com.project.personaltaskmanager.entities.Task;

public class TaskMapper {
    public static TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCategoryId(task.getCategory().getId());
        dto.setPriority(task.getPriority().name());
        dto.setStatus(task.getStatus().name());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    public static Task toEntity(TaskDto dto, Category category) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(category);
        task.setPriority(Priority.valueOf(dto.getPriority()));
        task.setStatus(Status.valueOf(dto.getStatus()));
        task.setDueDate(dto.getDueDate());
        return task;
    }
}