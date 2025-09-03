package com.project.personaltaskmanager.controller;
import com.project.personaltaskmanager.model.Category;
import com.project.personaltaskmanager.service.CategoryService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints for listing and fetching categories.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService service;

    public CategoryRestController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> listCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return service.getCategoryById(id);
    }
}
