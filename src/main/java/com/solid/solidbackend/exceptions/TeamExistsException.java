package com.solid.solidbackend.exceptions;

public class TeamExistsException extends RuntimeException{
    public TeamExistsException(String message) {
        super(message);
    }
}
