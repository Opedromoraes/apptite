package com.dev.apptite.domain.exceptions;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class ErrorObject {
    private final String message;
}
