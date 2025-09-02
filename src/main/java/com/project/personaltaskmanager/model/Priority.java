package com.project.personaltaskmanager.model;

public enum Priority {
    LOW("Low Priority", "#28a745", 1),
    MEDIUM("Medium Priority", "#ffc107", 2),
    HIGH("High Priority", "#dc3545", 3);

    private final String displayName;
    private final String colorCode;
    private final int orderValue;

    Priority(String displayName, String colorCode, int orderValue) {
        this.displayName = displayName;
        this.colorCode = colorCode;
        this.orderValue = orderValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public String getCssClass() {
        return switch (this) {
            case LOW -> "priority-low";
            case MEDIUM -> "priority-medium";
            case HIGH -> "priority-high";
        };
    }

    public String getBootstrapClass() {
        return switch (this) {
            case LOW -> "text-success";
            case MEDIUM -> "text-warning";
            case HIGH -> "text-danger";
        };
    }

    public String getIcon() {
        return switch (this) {
            case LOW -> "fas fa-arrow-down";
            case MEDIUM -> "fas fa-minus";
            case HIGH -> "fas fa-arrow-up";
        };
    }
}