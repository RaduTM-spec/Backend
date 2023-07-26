package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleNotAllowedException extends RuntimeException {
    public RoleNotAllowedException(String allowedRole) {
        super("Only `" + allowedRole + "` are allowed to do this!");
    }
}
