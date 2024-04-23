package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PagamentoDTO {
    Long idPagamento;
    Double valorTotalPedido;
    String metodoPagamento;
}
