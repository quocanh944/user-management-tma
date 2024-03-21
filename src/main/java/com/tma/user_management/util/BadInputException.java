package com.tma.user_management.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadInputException extends RuntimeException {
    public BadInputException() {
        super();
    }

    public BadInputException(final String message) {
        super(message);
    }
}
