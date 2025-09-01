package com.project.personaltaskmanager.exception;

import org.springframework.ui.Model;

public class GlobalExceptionHandler {
    public String handleRuntime(RuntimeException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}