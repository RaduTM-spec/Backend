package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(String teamName) {
        super("No team with name `" + teamName + "` was found!");
    }
}
