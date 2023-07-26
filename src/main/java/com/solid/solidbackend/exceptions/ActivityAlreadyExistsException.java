package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ActivityAlreadyExistsException extends RuntimeException {
    public ActivityAlreadyExistsException(String message) {
        super(message);
    }
}
