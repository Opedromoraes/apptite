package com.dev.apptite.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class BaseException extends RuntimeException {

    private final Object[] errors;
    private final HttpStatusCode status;

    public BaseException(String message, HttpStatusCode status, Object... errors) {
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
