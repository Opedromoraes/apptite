package com.dev.apptite.api.controller.pedido.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PedidoRequest {

    @Schema(description = "IDs dos itens", example = "1,2,3")
    @NotNull
    private List<Long> idsItens;

}