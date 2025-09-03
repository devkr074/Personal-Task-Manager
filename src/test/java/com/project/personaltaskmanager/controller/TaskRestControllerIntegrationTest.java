package com.project.personaltaskmanager.controller;
import com.project.personaltaskmanager.dto.TaskDTO;
import com.project.personaltaskmanager.model.Category;
import com.project.personaltaskmanager.model.Priority;
import com.project.personaltaskmanager.repository.CategoryRepository;
import com.project.personaltaskmanager.repository.PriorityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Verifies Task REST endpoints end-to-end.
 */
@SpringBootTest
@AutoConfigureMockMvc
class TaskRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private PriorityRepository priorityRepo;

    private Category work;
    private Priority high;

    @BeforeEach
    void setup() {
        work = categoryRepo.save(new Category("IntegrationWork"));
        high = priorityRepo.save(new Priority("IntegrationHigh"));
    }

    @Test
    void createAndFetchTask() throws Exception {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("Integration Test");
        dto.setCategoryId(work.getId());
        dto.setPriorityId(high.getId());
        dto.setDueDate(LocalDate.now().plusDays(1));

        String json = mapper.writeValueAsString(dto);

        // Create
        mvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.title").value("Integration Test"));

        // List
        mvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[?(@.title=='Integration Test')]").exists());
    }

    @Test
    void invalidCreateReturnsBadRequest() throws Exception {
        TaskDTO dto = new TaskDTO();
        dto.setTitle(""); // blank
        dto.setCategoryId(null);
        dto.setPriorityId(null);

        mvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.violations").isArray());
    }
}
