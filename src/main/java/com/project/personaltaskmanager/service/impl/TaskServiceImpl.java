package com.project.personaltaskmanager.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.entity.Task;
import com.project.personaltaskmanager.repository.TaskRepository;
import com.project.personaltaskmanager.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final ModelMapper mapper;

    public TaskServiceImpl(TaskRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<TaskDTO> findAll() {
        return repository.findAll().stream().map(task -> mapper.map(task, TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return mapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO create(TaskDTO dto) {
        Task task = mapper.map(dto, Task.class);
        task.setCreatedAt(LocalDateTime.now());
        Task saved = repository.save(task);
        return mapper.map(saved, TaskDTO.class);
    }

    @Override
    public TaskDTO update(Long id, TaskDTO dto) {
        Task existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        mapper.map(dto, existing);
        Task updated = repository.save(existing);
        return mapper.map(updated, TaskDTO.class);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}