package com.dev.apptite.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;


public class NotFoundException extends BaseException {

    public NotFoundException(String message, Object... args) {
        super(message, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), args);
    }

    public NotFoundException(String message) {
        super(message, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    public NotFoundException(String message, Throwable e) {
        super(message, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), e);
    }

}
