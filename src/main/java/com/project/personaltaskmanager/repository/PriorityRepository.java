package com.project.personaltaskmanager.repository;
import com.project.personaltaskmanager.model.Priority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
