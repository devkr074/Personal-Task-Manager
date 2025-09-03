package com.project.personaltaskmanager.service.impl;
import com.project.personaltaskmanager.exception.ResourceNotFoundException;
import com.project.personaltaskmanager.model.Priority;
import com.project.personaltaskmanager.repository.PriorityRepository;
import com.project.personaltaskmanager.service.PriorityService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Priority-related business logic.
 */
@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository repo;

    public PriorityServiceImpl(PriorityRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Priority> getAllPriorities() {
        return repo.findAll();
    }

    @Override
    public Priority getPriorityById(Long id) {
        return repo.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Priority not found with id " + id));
    }
}
