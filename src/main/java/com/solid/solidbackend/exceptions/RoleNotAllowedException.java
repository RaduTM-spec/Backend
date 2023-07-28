package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a user with an unauthorized role attempts to perform a restricted action.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RoleNotAllowedException extends RuntimeException {
    public RoleNotAllowedException(String allowedRole) {
        super("Only `" + allowedRole + "` are allowed to do this!");
    }
}
