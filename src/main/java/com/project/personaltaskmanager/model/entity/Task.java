package com.project.personaltaskmanager.model.entity;

import com.project.personaltaskmanager.model.enums.Priority;
import com.project.personaltaskmanager.model.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks", indexes = {
        @Index(name = "idx_task_status", columnList = "status"),
        @Index(name = "idx_task_priority", columnList = "priority"),
        @Index(name = "idx_task_due_date", columnList = "due_date"),
        @Index(name = "idx_task_category", columnList = "category_id")
})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Task title cannot be blank")
    @Size(min = 3, max = 100, message = "Task title must be between 3 and 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Size(max = 1000, message = "Task description cannot exceed 1000 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority = Priority.MEDIUM;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status = TaskStatus.PENDING;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    @Column(name = "active")
    private Boolean active = true;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_task_category"))
    private Category category;

    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description, Priority priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        if (status == TaskStatus.COMPLETED && this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        } else if (status != TaskStatus.COMPLETED) {
            this.completedAt = null;
        }
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isOverdue() {
        return dueDate != null &&
                dueDate.isBefore(LocalDate.now()) &&
                !status.isFinished();
    }

    public boolean isDueToday() {
        return dueDate != null && dueDate.equals(LocalDate.now());
    }

    public boolean isDueSoon() {
        if (dueDate == null)
            return false;
        LocalDate weekFromNow = LocalDate.now().plusDays(7);
        return dueDate.isAfter(LocalDate.now()) &&
                (dueDate.isBefore(weekFromNow) || dueDate.equals(weekFromNow));
    }

    public long getDaysUntilDue() {
        if (dueDate == null)
            return Long.MAX_VALUE;
        return LocalDate.now().until(dueDate).getDays();
    }

    public void markCompleted() {
        this.status = TaskStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public void markCancelled() {
        this.status = TaskStatus.CANCELLED;
        this.completedAt = LocalDateTime.now();
    }

    public void startProgress() {
        if (this.status == TaskStatus.PENDING) {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }

    public void resetToPending() {
        if (this.status.isEditable()) {
            this.status = TaskStatus.PENDING;
            this.completedAt = null;
        }
    }

    public boolean canBeEdited() {
        return status.isEditable() && active;
    }

    public boolean isCompleted() {
        return status == TaskStatus.COMPLETED;
    }

    public boolean isHighPriority() {
        return priority == Priority.HIGH || priority == Priority.URGENT;
    }

    public String getFormattedDueDate() {
        if (dueDate == null) {
            return "No due date";
        }

        if (isDueToday()) {
            return "Due today";
        } else if (isOverdue()) {
            long daysOverdue = Math.abs(getDaysUntilDue());
            return "Overdue by " + daysOverdue + (daysOverdue == 1 ? " day" : " days");
        } else {
            long daysUntil = getDaysUntilDue();
            if (daysUntil == 1) {
                return "Due tomorrow";
            } else if (daysUntil <= 7) {
                return "Due in " + daysUntil + " days";
            } else {
                return "Due " + dueDate.toString();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", dueDate=" + dueDate +
                ", category=" + (category != null ? category.getName() : "None") +
                ", overdue=" + isOverdue() +
                '}';
    }
}