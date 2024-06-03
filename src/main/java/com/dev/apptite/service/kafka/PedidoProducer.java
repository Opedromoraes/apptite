package com.dev.apptite.service.kafka;

import com.dev.apptite.domain.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoProducer {

    private static final String TOPIC = "pedido_status";
    private final KafkaTemplate<String, PedidoDTO> kafkaTemplate;

    public void enviarPedidoStatus(PedidoDTO pedidoStatus) {
        kafkaTemplate.send(TOPIC, pedidoStatus);
    }
}