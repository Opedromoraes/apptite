package com.dev.apptite.domain.exceptions;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ErroDTO {
    private final String message;
    private final List<String> errors;
}
