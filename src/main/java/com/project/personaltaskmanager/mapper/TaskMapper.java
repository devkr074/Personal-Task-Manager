package com.project.personaltaskmanager.mapper;

import com.project.personaltaskmanager.dto.TaskDto;
import com.project.personaltaskmanager.entity.Task;

public class TaskMapper {
    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setCategory_id(task.getCategory_id());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDuDate(task.getDuDate());
        dto.setPriority(task.getPriority());
        return dto;
    }

    public Task toEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setId(dto.getId());
        task.setCategory_id(dto.getCategory_id());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDuDate(dto.getDuDate());
        task.setPriority(dto.getPriority());
        return task;
    }
}