package com.dev.apptite.api.controller.cardapio.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CardapioRequest {

    @Schema(description = "Nome do cardapio", example = "Cardapio")
    @NotNull
    private String nome;

    @Schema(description = "Categorias", example = "1,2,3")
    private List<Long> categoriasId;

}
