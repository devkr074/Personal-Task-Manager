package com.project.personaltaskmanager.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.project.personaltaskmanager.controller.TaskRestController;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.mapper.TaskMapper;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.service.TaskService;

@WebMvcTest(TaskRestController.class)
class TaskRestControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    TaskService service;
    @MockBean
    TaskMapper mapper;

    @Test
    void createApi_returnsCreatedDto() throws Exception {
        String payload = "{\"title\":\"API Task\",\"description\":\"d\",\"dueDate\":\"2025-11-30\"}";
        Task entity = new Task();
        entity.setTitle("API Task");
        TaskDTO dto = new TaskDTO();
        dto.setTitle("API Task");
        when(mapper.toEntity(any())).thenReturn(entity);
        when(service.createTask(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);
        mvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("API Task"));
    }
}