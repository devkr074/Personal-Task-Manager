package com.project.personaltaskmanager.dto;

import java.time.LocalDate;
import com.project.personaltaskmanager.entity.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskDto {
    private Long id;
    @NotNull(message = "Category is required")
    private Long category_id;
    @NotNull(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;
    @Size(max = 100, message = "Description cannot exceed 100 characters")
    private String description;
    @NotNull(message = "Priority is required")
    private Priority priority;
    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate duDate;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDuDate() {
        return duDate;
    }

    public void setDuDate(LocalDate duDate) {
        this.duDate = duDate;
    }

    public boolean isCompleted() {
        return completed;
    }
}