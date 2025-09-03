package com.project.personaltaskmanager.controller;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Exposes Task CRUD operations over REST.
 */
@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> listTasks(
        @RequestParam(required = false) Boolean completed,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String priority,
        @ParameterObject Pageable pageable
    ) {
        // For simplicity, ignore filters and pagination combination
        if (completed != null) {
            return taskService.getAllTasks().stream()
                .filter(t -> t.isCompleted() == completed)
                .toList();
        }
        // Additional filtering logic can be moved to service layer
        if (pageable.isPaged()) {
            return taskService.getAllTasks()
                .stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .toList();
        }
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDTO dto) {
        Task created = taskService.createTask(dto);
        return ResponseEntity
            .status(201)
            .body(created);
    }

    @PutMapping("/{id}")
    public Task updateTask(
        @PathVariable Long id,
        @Valid @RequestBody TaskDTO dto
    ) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
