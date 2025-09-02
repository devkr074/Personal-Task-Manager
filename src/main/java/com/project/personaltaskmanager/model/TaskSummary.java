package com.project.personaltaskmanager.model;

public class TaskSummary {
    private long totalTasks;
    private long completedTasks;
    private long pendingTasks;
    private long inProgressTasks;
    private long overdueTasks;
    private long dueSoonTasks;

    public TaskSummary() {
    }

    public TaskSummary(long totalTasks, long completedTasks, long pendingTasks,
            long inProgressTasks, long overdueTasks, long dueSoonTasks) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.pendingTasks = pendingTasks;
        this.inProgressTasks = inProgressTasks;
        this.overdueTasks = overdueTasks;
        this.dueSoonTasks = dueSoonTasks;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public long getPendingTasks() {
        return pendingTasks;
    }

    public void setPendingTasks(long pendingTasks) {
        this.pendingTasks = pendingTasks;
    }

    public long getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(long inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public long getOverdueTasks() {
        return overdueTasks;
    }

    public void setOverdueTasks(long overdueTasks) {
        this.overdueTasks = overdueTasks;
    }

    public long getDueSoonTasks() {
        return dueSoonTasks;
    }

    public void setDueSoonTasks(long dueSoonTasks) {
        this.dueSoonTasks = dueSoonTasks;
    }

    public long getActiveTasks() {
        return pendingTasks + inProgressTasks;
    }

    public double getCompletionPercentage() {
        if (totalTasks == 0) {
            return 0.0;
        }
        return (double) completedTasks / totalTasks * 100;
    }

    public double getProgressPercentage() {
        if (totalTasks == 0) {
            return 0.0;
        }
        return (double) (completedTasks + inProgressTasks) / totalTasks * 100;
    }

    public boolean hasOverdueTasks() {
        return overdueTasks > 0;
    }

    public boolean hasDueSoonTasks() {
        return dueSoonTasks > 0;
    }

    @Override
    public String toString() {
        return "TaskSummary{" +
                "totalTasks=" + totalTasks +
                ", completedTasks=" + completedTasks +
                ", pendingTasks=" + pendingTasks +
                ", inProgressTasks=" + inProgressTasks +
                ", overdueTasks=" + overdueTasks +
                ", dueSoonTasks=" + dueSoonTasks +
                '}';
    }
}