package com.project.personaltaskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.project.personaltaskmanager.service.CategoryService;
import com.project.personaltaskmanager.service.TaskService;

@Controller
public class HomeController {
    private final TaskService taskService;
    private final CategoryService categoryService;

    public HomeController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        long totalTasks = taskService.getAllTasks().size();
        long completedTasks = taskService.getTasksByStatus("COMPLETED").size();
        long pendingTasks = taskService.getTasksByStatus("PENDING").size();
        long totalCategories = categoryService.getAllCategories().size();
        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("pendingTasks", pendingTasks);
        model.addAttribute("totalCategories", totalCategories);
        return "home";
    }
}