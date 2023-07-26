package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AssessmentNotFoundByIdException extends RuntimeException{
    public AssessmentNotFoundByIdException(String assessmentId) {
        super("Assessment with ID: `" + assessmentId + "` was not found!");
    }
}
