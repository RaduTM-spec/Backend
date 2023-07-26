package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AssessmentNotFoundException extends RuntimeException{
    public AssessmentNotFoundException(String assessmentName) {
        super("Assessment with name `" + assessmentName + "` was not found!");
    }
}
