package com.solid.solidbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyJoinedActivityException  extends RuntimeException{
    public AlreadyJoinedActivityException(String activityName) {
        super("Activity with name `" + activityName + "` was already joined!");
    }
}
