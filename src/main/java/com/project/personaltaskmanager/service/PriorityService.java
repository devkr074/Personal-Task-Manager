package com.project.personaltaskmanager.service;
import com.project.personaltaskmanager.model.Priority;
import java.util.List;

/**
 * Business operations for task priorities.
 */
public interface PriorityService {
    List<Priority> getAllPriorities();
    Priority getPriorityById(Long id);
}
