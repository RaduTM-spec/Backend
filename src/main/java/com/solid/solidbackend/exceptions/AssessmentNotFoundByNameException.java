package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to retrieve an assessment that is not registered in the system.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssessmentNotFoundByNameException extends RuntimeException {
    public AssessmentNotFoundByNameException(String assessmentName) {
        super("Assessment with name `" + assessmentName + "` was not found!");
    }
}
