package com.project.personaltaskmanager.controller;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.model.Category;
import com.project.personaltaskmanager.model.Priority;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.service.CategoryService;
import com.project.personaltaskmanager.service.PriorityService;
import com.project.personaltaskmanager.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Serves HTML pages for task operations.
 */
@Controller
@RequestMapping("/tasks")
public class TaskWebController {

  private final TaskService taskService;
  private final CategoryService categoryService;
  private final PriorityService priorityService;

  public TaskWebController(
      TaskService taskService,
      CategoryService categoryService,
      PriorityService priorityService
  ) {
    this.taskService = taskService;
    this.categoryService = categoryService;
    this.priorityService = priorityService;
  }

  @GetMapping
  public String listTasks(Model model) {
    model.addAttribute("tasks", taskService.getAllTasks());
    return "tasks/list";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("taskDTO", new TaskDTO());
    loadFormOptions(model);
    return "tasks/form";
  }

  @PostMapping
  public String createTask(
      @Valid @ModelAttribute("taskDTO") TaskDTO dto,
      BindingResult result,
      Model model
  ) {
    if (result.hasErrors()) {
      loadFormOptions(model);
      return "tasks/form";
    }
    taskService.createTask(dto);
    return "redirect:/tasks";
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    Task task = taskService.getTaskById(id);
    model.addAttribute("taskDTO", new TaskDTO(task));
    loadFormOptions(model);
    return "tasks/form";
  }

  @PostMapping("/{id}")
  public String updateTask(
      @PathVariable Long id,
      @Valid @ModelAttribute("taskDTO") TaskDTO dto,
      BindingResult result,
      Model model
  ) {
    if (result.hasErrors()) {
      loadFormOptions(model);
      return "tasks/form";
    }
    taskService.updateTask(id, dto);
    return "redirect:/tasks";
  }

  @GetMapping("/delete/{id}")
  public String deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return "redirect:/tasks";
  }

  private void loadFormOptions(Model model) {
    List<Category> categories = categoryService.getAllCategories();
    List<Priority> priorities = priorityService.getAllPriorities();
    model.addAttribute("categories", categories);
    model.addAttribute("priorities", priorities);
  }
}
