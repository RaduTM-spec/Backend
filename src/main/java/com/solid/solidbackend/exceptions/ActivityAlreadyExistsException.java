package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create an activity with a name that already exists in the system.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ActivityAlreadyExistsException extends RuntimeException {
    public ActivityAlreadyExistsException(String activityName) {
        super("An activity with the name `" + activityName + "` already exists!");
    }
}
