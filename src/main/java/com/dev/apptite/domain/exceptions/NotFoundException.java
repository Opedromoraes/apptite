package com.dev.apptite.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


public class NotFoundException extends BaseException {

    public NotFoundException(String message, Object... errors) {
        super(message, HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()), errors);
    }

    public NotFoundException(String message) {
        super(message, HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }
}
