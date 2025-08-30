package com.project.personaltaskmanager.mapper;

import org.springframework.stereotype.Component;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.model.Task;

@Component
public class TaskMapper {
    public Task toEntity(TaskDTO dto) {
        Task t = new Task();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setDueDate(dto.getDueDate());
        return t;
    }

    public TaskDTO toDto(Task entity) {
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDueDate(entity.getDueDate());
        return dto;
    }
}