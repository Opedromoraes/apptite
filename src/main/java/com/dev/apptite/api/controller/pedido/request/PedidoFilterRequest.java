package com.dev.apptite.api.controller.pedido.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PedidoFilterRequest {

    @Schema(description = "Status do pedido", example = "Pronto")
    private String status;

}