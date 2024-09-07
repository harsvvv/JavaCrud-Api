package org.example.firstproject.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {

        super(message);
    }
}
