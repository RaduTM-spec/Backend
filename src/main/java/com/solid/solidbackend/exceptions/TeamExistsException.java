package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when attempting to create a team with a name that already exists in the system.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamExistsException extends RuntimeException {
    public TeamExistsException(String teamName) {
        super(" Team with name `" + teamName + "` already exists! ");
    }
}
