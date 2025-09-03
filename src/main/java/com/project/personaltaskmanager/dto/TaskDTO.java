package com.project.personaltaskmanager.dto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import com.project.personaltaskmanager.model.Task;

/**
 * TaskDTO carries task data between layers,
 * enforcing validation rules.
 */
public class TaskDTO {

    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Priority ID is required")
    private Long priorityId;

    private boolean completed = false;
public TaskDTO() {
}
public TaskDTO(Task task) {
    this.id         = task.getId();
    this.title      = task.getTitle();
    this.categoryId = task.getCategory().getId();
    this.priorityId = task.getPriority().getId();
    this.dueDate    = task.getDueDate();
    this.completed  = task.isCompleted();
  }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public boolean isCompleted(){
        return completed;
    }
}
