package com.dev.apptite.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusPedidoEnum {

    PEDIDO_REALIZADO,
    PRODUZINDO_PEDIDO,
    AGUARDANDO_RETIRADA,
    PEDIDO_ENTREGUE,
    PEDIDO_INCORRETO,
    AGUARDANDO_PAGAMENTO,
    PROCESSANDO_PAGAMENTO,
    PAGAMENTO_RECUSADO,
    PAGAMENTO_CONCLUIDO;

}
