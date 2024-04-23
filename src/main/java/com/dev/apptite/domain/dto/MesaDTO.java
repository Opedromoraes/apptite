package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class MesaDTO {
    Long idMesa;
    String QRCode;
    PedidoDTO pedidoAtual;
}
