package com.dev.apptite.service.kafka;

import com.dev.apptite.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoConsumer {

    private final PedidoService service;

//    @KafkaListener(topics = "pedido_status", groupId = "pedido_group")
//    public void consumir(PedidoDTO pedidoDTO, Acknowledgment ack) {
//
//        service.atualizarStatusPedido(pedidoDTO.getIdPedido(), pedidoDTO.getStatus());
//        ack.acknowledge();
//    }
}