package com.dev.apptite.api.controller.pedido.request;

import com.dev.apptite.domain.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PedidoRequest {

    @Schema(description = "Status do pedido", example = "Pronto")
    @NotNull
    private String status;

    @Schema(description = "ID do restaurante", example = "1")
    @NotNull
    private List<Item> item;
}