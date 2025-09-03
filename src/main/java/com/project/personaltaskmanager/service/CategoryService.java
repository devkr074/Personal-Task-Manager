package com.project.personaltaskmanager.service;
import com.project.personaltaskmanager.model.Category;
import java.util.List;

/**
 * Business operations for task categories.
 */
public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
