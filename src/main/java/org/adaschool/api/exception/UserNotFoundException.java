package org.adaschool.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "user with ID: " + id + " not found");
    }
}
