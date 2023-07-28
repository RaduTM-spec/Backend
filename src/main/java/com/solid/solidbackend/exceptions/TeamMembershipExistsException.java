package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a user attempts to enroll in a team they are already a member of.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamMembershipExistsException extends RuntimeException {
    public TeamMembershipExistsException(String team) {
        super("Already enrolled in team with name: `" + team + "` !" );
    }

}
