package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoActivityFoundException extends RuntimeException{
    public NoActivityFoundException(String message) {
        super(message);
    }
}
