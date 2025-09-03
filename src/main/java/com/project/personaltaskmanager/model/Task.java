package com.project.personaltaskmanager.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Core entity representing a personal task.
 */
@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(length = 500)
  private String description;

  private LocalDate dueDate;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "priority_id", nullable = false)
  private Priority priority;

  private boolean completed = false;

  public Task() {}

  // Getters and setters...

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public LocalDate getDueDate() { return dueDate; }
  public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }
  public Priority getPriority() { return priority; }
  public void setPriority(Priority priority) { this.priority = priority; }
  public boolean isCompleted() { return completed; }
  public void setCompleted(boolean completed) { this.completed = completed; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Task)) return false;
    return Objects.equals(id, ((Task)o).id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
