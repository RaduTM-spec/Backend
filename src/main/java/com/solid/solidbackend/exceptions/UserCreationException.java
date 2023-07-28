package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create a user with a username that already exists in the system.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserCreationException extends RuntimeException {

    public UserCreationException(String userName) {
        super("User with username `" + userName + "` already exists!");
    }
}
