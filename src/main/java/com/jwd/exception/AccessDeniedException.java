package com.jwd.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends CustomException {
    public AccessDeniedException() {
        super("Access denied", HttpStatus.UNAUTHORIZED);
    }
}
