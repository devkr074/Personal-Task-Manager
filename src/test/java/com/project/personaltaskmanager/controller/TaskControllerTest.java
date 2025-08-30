package com.project.personaltaskmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.service.TaskService;

public class TaskControllerTest {
    private TaskService service;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        service = mock(TaskService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TaskController(service)).build();
    }

    @Test
    void listTasks_renderListView() throws Exception {
        when(service.getAllTasks()).thenReturn(List.of(new Task()));
        mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andExpect(view().name("tasks/list"))
                .andExpect(model().attributeExists("tasks"));
    }

    @Test
    void showForm_rendersForm() throws Exception {
        mockMvc.perform(get("/tasks/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/form"))
                .andExpect(model().attributeExists("task"));
    }

    @Test
    void create_withValidData_redirects() throws Exception {
        mockMvc.perform(post("/tasks")
                .param("title", "Test")
                .param("description", "Desc")
                .param("dueDate", "2025-12-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
        verify(service).createTask(any(Task.class));
    }

    @Test
    void create_withBlankTitle_returnsForm() throws Exception {
        mockMvc.perform(post("/tasks")
                .param("title", "")
                .param("description", "Desc"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/form"))
                .andExpect(model().attributeHasFieldErrors("task", "title"));
    }
}