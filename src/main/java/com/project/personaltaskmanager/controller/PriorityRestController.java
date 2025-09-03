package com.project.personaltaskmanager.controller;
import com.project.personaltaskmanager.model.Priority;
import com.project.personaltaskmanager.service.PriorityService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints for listing and fetching priorities.
 */
@RestController
@RequestMapping("/api/priorities")
public class PriorityRestController {

    private final PriorityService service;

    public PriorityRestController(PriorityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Priority> listPriorities() {
        return service.getAllPriorities();
    }

    @GetMapping("/{id}")
    public Priority getPriority(@PathVariable Long id) {
        return service.getPriorityById(id);
    }
}
