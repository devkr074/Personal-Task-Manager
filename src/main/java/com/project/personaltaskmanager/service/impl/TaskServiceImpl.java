package com.project.personaltaskmanager.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.personaltaskmanager.model.Task;
import com.project.personaltaskmanager.repository.TaskRepository;
import com.project.personaltaskmanager.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        log.debug("Fetching all tasks");
        return repository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        log.debug("Creating new task: {}", task.getTitle());
        return repository.save(task);
    }
}