package com.dev.apptite.api.controller.item.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ItemRequest {

    @Schema(description = "Nome do item", example = "X-egg bacon")
    @NotNull
    private String nome;

    @Schema(description = "Descrição do item", example = "Bife de hambúrguer, queijo, ovo, bacon, alface, tomate e molho especial no pão")
    private String descricao;

    @Schema(description = "Preço do item", example = "17.50")
    private double preco;

    @Schema(description = "Identificador da categoria", example = "1")
    @NotNull
    private Long idCategoria;
}