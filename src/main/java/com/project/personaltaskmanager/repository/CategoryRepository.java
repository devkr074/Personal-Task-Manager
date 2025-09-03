package com.project.personaltaskmanager.repository;
import com.project.personaltaskmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
