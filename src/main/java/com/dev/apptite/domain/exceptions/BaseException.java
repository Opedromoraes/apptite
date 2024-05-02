package com.dev.apptite.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
public class BaseException extends RuntimeException {

    private final List<String> errors;
    private final HttpStatusCode status;

    public BaseException(String message, HttpStatusCode status, List<String> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

    public BaseException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.status = httpStatusCode;
        this.errors = null;
    }
}
