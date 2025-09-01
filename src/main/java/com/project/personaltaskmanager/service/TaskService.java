package com.project.personaltaskmanager.service;

import java.util.List;
import com.project.personaltaskmanager.dto.TaskDTO;

public interface TaskService {
    List<TaskDTO> findAll();

    TaskDTO findById(Long id);

    TaskDTO create(TaskDTO taskDTO);

    TaskDTO update(Long id, TaskDTO taskDTO);

    void delete(Long id);
}