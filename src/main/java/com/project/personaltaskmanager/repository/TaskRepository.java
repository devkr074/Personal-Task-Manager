package com.project.personaltaskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.personaltaskmanager.entities.Status;
import com.project.personaltaskmanager.entities.Task;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

    List<Task> findByCategoryId(Long categoryId);
}