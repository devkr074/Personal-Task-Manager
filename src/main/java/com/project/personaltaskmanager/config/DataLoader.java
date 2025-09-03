package com.project.personaltaskmanager.config;

import com.project.personaltaskmanager.model.entity.Category;
import com.project.personaltaskmanager.model.entity.Task;
import com.project.personaltaskmanager.model.enums.Priority;
import com.project.personaltaskmanager.model.enums.TaskStatus;
import com.project.personaltaskmanager.repository.CategoryRepository;
import com.project.personaltaskmanager.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class DataLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    public DataLoader(CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        logger.info("Starting data initialization...");
        try {
            if (categoryRepository.count() == 0 && taskRepository.count() == 0) {
                loadInitialData();
                logger.info("Sample data loaded successfully");
            } else {
                logger.info("Database already contains data, skipping initialization");
            }
        } catch (Exception e) {
            logger.error("Error loading initial data", e);
        }
    }

    private void loadInitialData() {
        logger.debug("Loading initial categories and tasks...");
        List<Category> categories = createDefaultCategories();
        categoryRepository.saveAll(categories);
        logger.debug("Created {} default categories", categories.size());
        List<Task> tasks = createSampleTasks(categories);
        taskRepository.saveAll(tasks);
        logger.debug("Created {} sample tasks", tasks.size());
        logDataSummary();
    }

    private List<Category> createDefaultCategories() {
        return Arrays.asList(
                new Category("Work", "Professional and work-related tasks", "#007bff"),
                new Category("Personal", "Personal tasks and activities", "#28a745"),
                new Category("Health", "Health and fitness related tasks", "#dc3545"),
                new Category("Learning", "Educational and skill development tasks", "#ffc107"),
                new Category("Finance", "Financial and money-related tasks", "#6f42c1"),
                new Category("Home", "Household chores and maintenance", "#fd7e14"),
                new Category("Social", "Social activities and relationships", "#20c997"),
                new Category("Shopping", "Shopping lists and purchases", "#e83e8c"));
    }

    private List<Task> createSampleTasks(List<Category> categories) {
        Category work = findCategoryByName(categories, "Work");
        Category personal = findCategoryByName(categories, "Personal");
        Category health = findCategoryByName(categories, "Health");
        Category learning = findCategoryByName(categories, "Learning");
        Category home = findCategoryByName(categories, "Home");
        return Arrays.asList(
                createTask("Complete project proposal",
                        "Finish the Q4 project proposal document and submit for review",
                        Priority.HIGH, TaskStatus.IN_PROGRESS, LocalDate.now().plusDays(2), work),
                createTask("Team meeting preparation",
                        "Prepare agenda and materials for weekly team meeting",
                        Priority.MEDIUM, TaskStatus.PENDING, LocalDate.now().plusDays(1), work),
                createTask("Code review for feature X",
                        "Review pull request #123 for new authentication feature",
                        Priority.HIGH, TaskStatus.PENDING, LocalDate.now(), work),
                createTask("Update project documentation",
                        "Update README and API documentation for the latest changes",
                        Priority.LOW, TaskStatus.PENDING, LocalDate.now().plusDays(7), work),
                createTask("Buy groceries",
                        "Weekly grocery shopping - milk, bread, fruits, vegetables",
                        Priority.MEDIUM, TaskStatus.PENDING, LocalDate.now(), personal),
                createTask("Call family",
                        "Weekly check-in call with parents and siblings",
                        Priority.HIGH, TaskStatus.PENDING, LocalDate.now().plusDays(1), personal),
                createTask("Plan weekend trip",
                        "Research and book accommodation for weekend getaway",
                        Priority.LOW, TaskStatus.COMPLETED, null, personal),
                createTask("Morning workout",
                        "30-minute cardio and strength training session",
                        Priority.HIGH, TaskStatus.COMPLETED, LocalDate.now().minusDays(1), health),
                createTask("Doctor's appointment",
                        "Annual health check-up with Dr. Smith",
                        Priority.URGENT, TaskStatus.PENDING, LocalDate.now().plusDays(3), health),
                createTask("Meal prep for the week",
                        "Prepare healthy meals for Monday through Friday",
                        Priority.MEDIUM, TaskStatus.PENDING, LocalDate.now().plusDays(1), health),
                createTask("Complete Spring Boot course",
                        "Finish the advanced Spring Boot course on Udemy - 3 chapters remaining",
                        Priority.MEDIUM, TaskStatus.IN_PROGRESS, LocalDate.now().plusDays(14), learning),
                createTask("Read 'Clean Code' book",
                        "Continue reading Clean Code by Robert Martin - currently on chapter 5",
                        Priority.LOW, TaskStatus.IN_PROGRESS, LocalDate.now().plusDays(30), learning),
                createTask("Fix leaky faucet",
                        "Repair the kitchen faucet that's been dripping",
                        Priority.MEDIUM, TaskStatus.PENDING, LocalDate.now().plusDays(5), home),
                createTask("Organize garage",
                        "Clean and organize garage storage area",
                        Priority.LOW, TaskStatus.PENDING, LocalDate.now().plusDays(10), home),
                createTask("Submit tax documents",
                        "Gather and submit all required tax documents",
                        Priority.URGENT, TaskStatus.PENDING, LocalDate.now().minusDays(2), personal),

                createTask("Renew car insurance",
                        "Review and renew annual car insurance policy",
                        Priority.HIGH, TaskStatus.PENDING, LocalDate.now().minusDays(1), personal));
    }

    private Task createTask(String title, String description, Priority priority,
            TaskStatus status, LocalDate dueDate, Category category) {
        Task task = new Task(title, description, priority, dueDate);
        task.setStatus(status);
        task.setCategory(category);
        return task;
    }

    private Category findCategoryByName(List<Category> categories, String name) {
        return categories.stream()
                .filter(cat -> cat.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private void logDataSummary() {
        long categoryCount = categoryRepository.count();
        long taskCount = taskRepository.count();
        long pendingTasks = taskRepository.countByStatus(TaskStatus.PENDING);
        long inProgressTasks = taskRepository.countByStatus(TaskStatus.IN_PROGRESS);
        long completedTasks = taskRepository.countByStatus(TaskStatus.COMPLETED);
        logger.info("=================================================================");
        logger.info("    Data Loading Summary");
        logger.info("=================================================================");
        logger.info("    Categories created: {}", categoryCount);
        logger.info("    Tasks created: {}", taskCount);
        logger.info("    - Pending: {}", pendingTasks);
        logger.info("    - In Progress: {}", inProgressTasks);
        logger.info("    - Completed: {}", completedTasks);
        logger.info("=================================================================");
    }
}