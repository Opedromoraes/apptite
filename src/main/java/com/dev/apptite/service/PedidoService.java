package com.dev.apptite.service;

import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public void atualizarStatusPedido(Long idPedido, String status) {

        Pedido pedido = repository.buscarPorId(idPedido);
        pedido.setStatus(status);
        repository.salvar(pedido);
    }

}
