package com.dev.apptite.domain.exceptions.handler;

import com.dev.apptite.domain.exceptions.BaseException;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import com.dev.apptite.domain.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageUtils messageUtils;

    @ExceptionHandler({
            BusinessException.class,
            NotFoundException.class
    })
    public ResponseEntity<ErrorDTO> handleBusinessException(BaseException ex) {

        List<String> errors = new ArrayList<>();
        if (ex.getErrors() != null && !ex.getErrors().isEmpty()){
            errors.addAll(ex.getErrors().stream()
                    .map(messageUtils::getMessage)
                    .toList());
        }

        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(messageUtils.getMessage(ex.getMessage()))
                .errors(errors)
                .build();

        return ResponseEntity.status(ex.getStatus()).body(errorDTO);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(objectError -> messageUtils.getMessage("field.required", objectError.getField()))
                .toList();

        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(messageUtils.getMessage("base.message.error"))
                .errors(errors)
                .build();


        return ResponseEntity.status(ex.getStatusCode()).body(errorDTO);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("Request body is not readable")
                .build();

        return ResponseEntity.status(status).headers(headers).body(errorDTO);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status, WebRequest request) {
        List<String> supportedMediaTypes = ex.getSupportedMediaTypes()
                .stream()
                .map(MediaType::toString)
                .toList();

        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("Supported media types: " + String.join(", ", supportedMediaTypes))
                .build();

        return ResponseEntity.status(status).headers(headers).body(errorDTO);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        log.error("Unexpected error", ex);

        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(messageUtils.getMessage("base.message.error-unexpected"))
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}