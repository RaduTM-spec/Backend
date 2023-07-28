package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to retrieve an assessment with an invalid or non-existent ID.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssessmentNotFoundByIdException extends Exception{
    public AssessmentNotFoundByIdException(String assessmentId) {
        super("Assessment with ID: `" + assessmentId + "` was not found!");
    }
}
