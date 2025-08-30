package com.project.personaltaskmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.mapper.TaskMapper;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.service.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
    private final TaskService service;
    private final TaskMapper mapper;

    public TaskRestController(TaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TaskDTO> getAll() {
        return service.getAllTasks().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO dto) {
        Task saved = service.createTask(mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }
}