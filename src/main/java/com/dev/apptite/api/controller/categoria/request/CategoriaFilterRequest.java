package com.dev.apptite.api.controller.categoria.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CategoriaFilterRequest {

    @Schema(description = "Nome da categoria", example = "Comidas")
    private String nome;

    @Schema(description = "Identificador do cardapio", example = "1")
    @NotNull
    private Long idCardapio;
}