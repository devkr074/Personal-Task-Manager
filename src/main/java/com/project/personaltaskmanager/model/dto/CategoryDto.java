package com.project.personaltaskmanager.model.dto;

import jakarta.validation.constraints.*;

public class CategoryDto {
    private Long id;
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Color must be a valid hex color code (e.g., #FF5733)")
    private String color = "#007bff";
    private int taskCount;
    private long completedTaskCount;
    private long activeTaskCount;

    public CategoryDto() {
    }

    public CategoryDto(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(long completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public long getActiveTaskCount() {
        return activeTaskCount;
    }

    public void setActiveTaskCount(long activeTaskCount) {
        this.activeTaskCount = activeTaskCount;
    }

    public double getCompletionPercentage() {
        if (taskCount == 0) {
            return 0.0;
        }
        return (double) completedTaskCount / taskCount * 100;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskCount=" + taskCount +
                '}';
    }
}