package com.solid.solidbackend.exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String message) {
        super(message);
    }
}
