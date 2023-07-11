package com.solid.solidbackend.Exceptions;

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
