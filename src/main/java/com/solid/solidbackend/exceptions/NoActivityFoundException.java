package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoActivityFoundException extends RuntimeException{
    public NoActivityFoundException(String activityName) {
        super("No activity with name `" + activityName + "` was found!");
    }
}
