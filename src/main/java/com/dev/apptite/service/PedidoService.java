package com.dev.apptite.service;

import com.dev.apptite.api.controller.pedido.request.PedidoFilterRequest;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import com.dev.apptite.domain.mapper.PedidoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoMapper mapper;
    private final PedidoRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final ItemService itemService;

    public PedidoDTO salvar(PedidoDTO pedidoDTO) {

        associarItem(pedidoDTO);
        Pedido pedido = mapper.dtoToEntity(pedidoDTO);
        pedido.setStatus(StatusPedidoEnum.PEDIDO_REALIZADO);
        return mapper.entityToDTO(repository.salvar(pedido));
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = repository.buscarPorId(id);
        return mapper.entityToDTO(pedido);
    }

    public void deletarPorId(Long id) {

        repository.deletarPorId(id);
    }

    public PedidoDTO processarPedido(StatusPedidoEnum status, Long id) {
        switch (status) {
            case PRODUZINDO_PEDIDO -> processarProduzindoPedido(id);
            case AGUARDANDO_RETIRADA -> processarAguardandoRetirada(id);
            case PEDIDO_ENTREGUE -> processarPedidoEntregue(id);
            case PEDIDO_INCORRETO -> processarPedidoIncorreto(id);
            case AGUARDANDO_PAGAMENTO -> processarAguardandoPagamento(id);
            case PROCESSANDO_PAGAMENTO -> processarProcessandoPagamento(id);
            case PAGAMENTO_RECUSADO -> processarPagamentoRecusado(id);
            case PAGAMENTO_CONCLUIDO -> processarPagamentoConcluido(id);

        }
        return null;
    }

    private void processarProduzindoPedido(Long id) {
        System.out.printf("Processando pedido de id %l", id);
    }

    private void processarAguardandoRetirada(Long id) {
        System.out.printf("Processando pedido de id %l", id);
    }

    private void processarPedidoEntregue(Long id) {
        System.out.printf("Pedido  de id %l entregue", id);
    }

    private void processarPedidoIncorreto(Long id) {
        System.out.printf("Pedido incorreto de id %l", id);
    }

    private void processarAguardandoPagamento(Long id) {
        System.out.printf("Aguardando pagamento do pedido de id %l", id);
    }

    private void processarProcessandoPagamento(Long id) {
        System.out.printf("Processando pagamento do pedido de id %l", id);
    }

    private void processarPagamentoRecusado(Long id) {
        System.out.printf("Pagamento recusado do pedido de id %l", id);
    }

    private void processarPagamentoConcluido(Long id) {
        System.out.printf("Pagamento concluido do pedido de id %l", id);
    }


    public PageResponse<PedidoDTO> findPaginated(PageRequest pageable, PedidoFilterRequest request) {

        Page<Pedido> itemPage = repository.buscarPorFiltroComPaginacao(pageable, request);
        PageResponse<Pedido> page = pageResponseMapper.pageToPageResponse(itemPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarItem(PedidoDTO pedidoDTO) {
        List<ItemDTO> itensDTO = new ArrayList<>();
        if (!pedidoDTO.getIdsItens().isEmpty()) {
            pedidoDTO.getIdsItens().forEach(id -> {
                ItemDTO itemDTO = itemService.buscarPorId(id);
                itensDTO.add(itemDTO);
            });
            pedidoDTO.setItens(itensDTO);
            pedidoDTO.setIdsItens(itensDTO.stream().map(ItemDTO::getIdItem).toList());
        }
    }
}
