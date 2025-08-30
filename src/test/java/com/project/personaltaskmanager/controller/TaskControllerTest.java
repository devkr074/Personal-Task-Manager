package com.project.personaltaskmanager.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}