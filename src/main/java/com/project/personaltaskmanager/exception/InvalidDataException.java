package com.project.personaltaskmanager.exception;
/**
 * Thrown when business-rule validation fails.
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
