package com.dev.apptite.api.controller.categoria.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequest {

    @Schema(description = "Nome da categoria", example = "Comidas")
    @NotNull
    private String nome;

    @Schema(description = "Identificador do restaurante", example = "1")
    @NotNull
    private Long idRestaurante;
}
