package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to retrieve a team membership that does not exist in the system.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamMembershipNotFoundException extends RuntimeException{
    public TeamMembershipNotFoundException(String message) {
        super(message);
    }
}
