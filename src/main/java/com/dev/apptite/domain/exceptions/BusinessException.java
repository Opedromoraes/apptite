package com.dev.apptite.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;


@Getter
public class BusinessException extends BaseException {

    public BusinessException(String message, List<String> errors) {
        super(message, HttpStatusCode.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), errors);
    }

    public BusinessException(String message) {
        super(message, HttpStatusCode.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
    }
}