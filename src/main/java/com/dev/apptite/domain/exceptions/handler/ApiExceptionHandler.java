package com.dev.apptite.domain.exceptions.handler;

import com.dev.apptite.domain.config.MessageService;
import com.dev.apptite.domain.exceptions.BaseException;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Stream;

@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler({
            BusinessException.class,
            NotFoundException.class
    })
    public ResponseEntity<Object> handleBusinessException(BaseException ex) {

        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(messageService.getMessage(ex.getMessage()))
                .errors(Stream.of(ex.getErrors()).map(String::valueOf).toList())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(errorDTO);
    }

}
