package com.project.personaltaskmanager.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.service.TaskService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String listTasks(Model model) {
        List<Task> tasks = service.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/form";
    }

    @PostMapping
    public String create(@ModelAttribute("task") @Valid Task task, BindingResult br) {
        if (br.hasErrors()) {
            return "tasks/form";
        }
        service.createTask(task);
        return "redirect:/tasks";
    }
}