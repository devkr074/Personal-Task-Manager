package com.project.personaltaskmanager.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.personaltaskmanager.dto.TaskDto;
import com.project.personaltaskmanager.service.CategoryService;
import com.project.personaltaskmanager.service.TaskService;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final CategoryService categoryService;

    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new TaskDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "tasks/create";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") TaskDto taskDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "tasks/create";
        }
        taskService.createTask(taskDto);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "tasks/edit";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute("task") TaskDto taskDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "tasks/edit";
        }
        taskService.updateTask(id, taskDto);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}