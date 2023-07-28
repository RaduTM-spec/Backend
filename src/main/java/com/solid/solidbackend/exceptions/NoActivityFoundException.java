package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to retrieve an activity with a name that doesent exist in the system.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoActivityFoundException extends RuntimeException {
    public NoActivityFoundException(String activityName) {
        super("No activity with name `" + activityName + "` was found!");
    }
}
