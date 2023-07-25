package com.solid.solidbackend.exceptions;

public class AssessmentNotFoundException extends RuntimeException{
    public AssessmentNotFoundException(String message) {
        super(message);
    }
}
