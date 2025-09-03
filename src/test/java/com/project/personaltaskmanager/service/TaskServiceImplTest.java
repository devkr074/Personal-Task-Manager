package com.project.personaltaskmanager.service;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.exception.InvalidDataException;
import com.project.personaltaskmanager.exception.ResourceNotFoundException;
import com.project.personaltaskmanager.model.Category;
import com.project.personaltaskmanager.model.Priority;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.repository.CategoryRepository;
import com.project.personaltaskmanager.repository.PriorityRepository;
import com.project.personaltaskmanager.repository.TaskRepository;
import com.project.personaltaskmanager.service.impl.TaskServiceImpl;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepo;

    @Mock
    private CategoryRepository categoryRepo;

    @Mock
    private PriorityRepository priorityRepo;

    @InjectMocks
    private TaskServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask_whenDueDateInPast_thenThrowInvalidDataException() {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("Test");
        dto.setCategoryId(1L);
        dto.setPriorityId(1L);
        dto.setDueDate(LocalDate.now().minusDays(1));

        assertThrows(InvalidDataException.class,
            () -> service.createTask(dto));
    }

    @Test
    void getTaskById_whenNotFound_thenThrowResourceNotFoundException() {
        when(taskRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
            () -> service.getTaskById(99L));
    }

    @Test
    void createTask_whenValid_thenReturnSavedTask() {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("New Task");
        dto.setCategoryId(1L);
        dto.setPriorityId(2L);
        dto.setDueDate(LocalDate.now().plusDays(3));

        Category category = new Category("Work");
        category.setId(1L);
        Priority priority = new Priority("Medium");
        priority.setId(2L);

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
        when(priorityRepo.findById(2L)).thenReturn(Optional.of(priority));

        Task saved = new Task();
        saved.setId(10L);
        saved.setTitle("New Task");
        saved.setCategory(category);
        saved.setPriority(priority);
        saved.setDueDate(dto.getDueDate());

        when(taskRepo.save(any(Task.class))).thenReturn(saved);

        Task result = service.createTask(dto);
        assertEquals(10L, result.getId());
        assertEquals("New Task", result.getTitle());
        verify(taskRepo, times(1)).save(any(Task.class));
    }
}
