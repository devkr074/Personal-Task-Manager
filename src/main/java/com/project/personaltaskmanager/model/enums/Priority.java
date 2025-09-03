package com.project.personaltaskmanager.model.enums;

public enum Priority {
    URGENT("Urgent", 4, "#dc3545"),
    HIGH("High", 3, "#fd7e14"),
    MEDIUM("Medium", 2, "#ffc107"),
    LOW("Low", 1, "#28a745");

    private final String displayName;
    private final int value;
    private final String color;

    Priority(String displayName, int value, String color) {
        this.displayName = displayName;
        this.value = value;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public static Priority fromDisplayName(String displayName) {
        if (displayName == null) {
            return null;
        }
        for (Priority priority : Priority.values()) {
            if (priority.displayName.equalsIgnoreCase(displayName)) {
                return priority;
            }
        }
        return null;
    }

    public static Priority fromValue(int value) {
        for (Priority priority : Priority.values()) {
            if (priority.value == value) {
                return priority;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}