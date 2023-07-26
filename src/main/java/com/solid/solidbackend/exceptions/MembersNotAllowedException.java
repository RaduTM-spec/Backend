package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MembersNotAllowedException extends RuntimeException {
    public MembersNotAllowedException(String message) {
        super(message);
    }
}
