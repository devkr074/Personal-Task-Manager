package com.project.personaltaskmanager.service.impl;
import com.project.personaltaskmanager.exception.ResourceNotFoundException;
import com.project.personaltaskmanager.model.Category;
import com.project.personaltaskmanager.repository.CategoryRepository;
import com.project.personaltaskmanager.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Category-related business logic.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Category not found with id " + id));
    }
}
