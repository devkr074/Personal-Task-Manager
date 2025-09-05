package com.project.personaltaskmanager.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.personaltaskmanager.dto.CategoryDto;
import com.project.personaltaskmanager.service.CategoryService;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "categories/create";
    }

    @PostMapping
    public String createCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/create";
        }
        categoryService.createCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categories/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "categories/edit";
        }
        categoryService.updateCategory(id, categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}