package com.project.personaltaskmanager.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.repository.TaskRepository;
import com.project.personaltaskmanager.service.impl.TaskServiceImpl;

public class TaskServiceTest {
    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repository = mock(TaskRepository.class);
        service = new TaskServiceImpl(repository);
    }

    @Test
    void getAllTasks_returnsAll() {
        when(repository.findAll()).thenReturn(List.of(new Task(), new Task()));
        List<Task> tasks = service.getAllTasks();
        assertThat(tasks).hasSize(2);
        verify(repository, times(1)).findAll();
    }
}