package com.dev.apptite.api.controller.item.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ItemRequest {

    @Schema(description = "Nome do item", example = "Coca-cola")
    @NotNull
    private String nome;

    @Schema(description = "Identificador da categoria", example = "1")
    @NotNull
    private Long idCategoria;
}