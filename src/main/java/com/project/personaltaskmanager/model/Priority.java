package com.project.personaltaskmanager.model;
import jakarta.persistence.*;
import java.util.Objects;

/**
 * Represents a priority level (Low, Medium, High).
 */
@Entity
@Table(name = "priorities")
public class Priority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 20)
  private String level;

  public Priority() {}

  public Priority(String level) { this.level = level; }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getLevel() { return level; }
  public void setLevel(String level) { this.level = level; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Priority)) return false;
    return Objects.equals(id, ((Priority)o).id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
