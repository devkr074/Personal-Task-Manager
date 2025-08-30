package com.project.personaltaskmanager.service;

import java.util.List;
import com.project.personaltaskmanager.model.Task;

public interface TaskService {
    List<Task> getAllTasks();

    Task createTask(Task task);
}