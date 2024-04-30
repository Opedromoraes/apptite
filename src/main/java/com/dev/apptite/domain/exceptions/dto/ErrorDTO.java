package com.dev.apptite.domain.exceptions.dto;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private final String message;
    private final List<String> errors;
    private final HttpStatusCode status;
}
