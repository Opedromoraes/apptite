package com.dev.apptite.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;


@Getter
public class BusinessException extends BaseException {

    public BusinessException(String message, Object... args) {
        super(message, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), args);
    }

    public BusinessException(String message) {
        super(message, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    public BusinessException(String message, Throwable e) {
        super(message, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), e);
    }
}
