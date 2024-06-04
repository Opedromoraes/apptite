package com.dev.apptite.api.controller.cardapio.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CardapioFilterRequest {

    @Schema(description = "Nome do cardapio", example = "Cardapio")
    @NotNull
    private String nome;

    @Schema(description = "ID do restaurante", example = "1")
    @NotNull
    private Long idRestaurante;
}