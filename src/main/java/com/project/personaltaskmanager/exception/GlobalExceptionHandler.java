package com.project.personaltaskmanager.exception;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Centralizes exception responses for all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidData(InvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
        MethodArgumentNotValidException ex) {

        ErrorResponse response = new ErrorResponse("Validation failed");
        for (FieldError field : ex.getBindingResult().getFieldErrors()) {
            response.addViolation(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }

    // DTO for validation error details
    public static class ErrorResponse {
        private String message;
        private List<Violation> violations = new ArrayList<>();

        public ErrorResponse(String message) {
            this.message = message;
        }

        public void addViolation(String field, String error) {
            this.violations.add(new Violation(field, error));
        }

        public String getMessage() {
            return message;
        }

        public List<Violation> getViolations() {
            return violations;
        }

        public static class Violation {
            private String field;
            private String error;

            public Violation(String field, String error) {
                this.field = field;
                this.error = error;
            }
public String getField() {
    return field;
}
public String getError() {
    return error;
}
        }
    }
}
