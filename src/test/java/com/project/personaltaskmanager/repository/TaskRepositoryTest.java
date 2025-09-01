package com.project.personaltaskmanager.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.personaltaskmanager.entity.Task;

public class TaskRepositoryTest {
    @Autowired
    private TaskRepository repository;

    void whenSave_thenFindById() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Repository save and find");
        task.setPriority("LOW");
        task.setCategory("Test");
        task.setCompleted(false);
        Task saved = repository.save(task);
        assertThat(repository.findById(saved.getId())).isPresent();
    }
}