package com.project.personaltaskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.personaltaskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}