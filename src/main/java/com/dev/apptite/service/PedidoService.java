package com.dev.apptite.service;

import com.dev.apptite.api.controller.pedido.request.PedidoFilterRequest;
import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.mapper.PedidoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoMapper mapper;
    private final PedidoRepository repository;
    private final PageResponseMapper pageResponseMapper;

    public PedidoDTO salvar(PedidoDTO pedidoDTO) {

        Pedido pedido = mapper.dtoToEntity(pedidoDTO);
        return mapper.entityToDTO(repository.salvar(pedido));
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = repository.buscarPorId(id);
        return mapper.entityToDTO(pedido);
    }

    public void deletarPorId(Long id) {

        repository.deletarPorId(id);
    }


    public PageResponse<PedidoDTO> findPaginated(PageRequest pageable, PedidoFilterRequest request) {

        Page<Pedido> itemPage = repository.buscarPorFiltroComPaginacao(pageable, request);
        PageResponse<Pedido> page = pageResponseMapper.pageToPageResponse(itemPage);
        return mapper.mapPageEntityToPageDto(page);
    }

}
