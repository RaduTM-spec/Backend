package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamMembershipExistsException extends RuntimeException{
    public TeamMembershipExistsException(String message)
    {
        super(message);
    }

}
