package com.project.personaltaskmanager.model.enums;

public enum TaskStatus {
    PENDING("Pending", "secondary", "⏳"),
    IN_PROGRESS("In Progress", "primary", "🔄"),
    COMPLETED("Completed", "success", "✅"),
    CANCELLED("Cancelled", "danger", "❌");

    private final String displayName;
    private final String bootstrapClass;
    private final String icon;

    TaskStatus(String displayName, String bootstrapClass, String icon) {
        this.displayName = displayName;
        this.bootstrapClass = bootstrapClass;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBootstrapClass() {
        return bootstrapClass;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isFinished() {
        return this == COMPLETED || this == CANCELLED;
    }

    public boolean isEditable() {
        return this != COMPLETED && this != CANCELLED;
    }

    public static TaskStatus fromDisplayName(String displayName) {
        if (displayName == null) {
            return null;
        }
        for (TaskStatus status : TaskStatus.values()) {
            if (status.displayName.equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}