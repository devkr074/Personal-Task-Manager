package com.project.personaltaskmanager.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskDTO {
    private Long id;
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be under 100 chars")
    private String title;
    @Size(max = 500, message = "Description must be under 100 chars")
    private String description;
    private LocalDate dueDate;

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
}