package com.solid.solidbackend.exceptions;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException()
    {
        super();
    }
    public InvalidUserException(String message)
    {
        super(message);
    }

}
