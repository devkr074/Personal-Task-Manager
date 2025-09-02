package com.project.personaltaskmanager.model;

public enum TaskStatus {
    PENDING("Pending", "#6c757d", "fas fa-clock"),
    IN_PROGRESS("In Progress", "#17a2b8", "fas fa-spinner"),
    COMPLETED("Completed", "#28a745", "fas fa-check-circle"),
    CANCELLED("Cancelled", "#dc3545", "fas fa-times-circle");

    private final String displayName;
    private final String colorCode;
    private final String icon;

    TaskStatus(String displayName, String colorCode, String icon) {
        this.displayName = displayName;
        this.colorCode = colorCode;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getIcon() {
        return icon;
    }

    public String getBootstrapClass() {
        return switch (this) {
            case PENDING -> "text-secondary";
            case IN_PROGRESS -> "text-info";
            case COMPLETED -> "text-success";
            case CANCELLED -> "text-danger";
        };
    }

    public String getBadgeClass() {
        return switch (this) {
            case PENDING -> "badge bg-secondary";
            case IN_PROGRESS -> "badge bg-info";
            case COMPLETED -> "badge bg-success";
            case CANCELLED -> "badge bg-danger";
        };
    }

    public boolean isActive() {
        return this == PENDING || this == IN_PROGRESS;
    }

    public boolean isCompleted() {
        return this == COMPLETED;
    }
}