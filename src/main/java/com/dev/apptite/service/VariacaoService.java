package com.dev.apptite.service;

import com.dev.apptite.api.controller.variacao.request.VariacaoFilterRequest;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.dto.VariacaoDTO;
import com.dev.apptite.domain.entity.Variacao;
import com.dev.apptite.domain.mapper.VariacaoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.VariacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VariacaoService {

    private final VariacaoMapper mapper;
    private final VariacaoRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final ItemService itemService;

    public VariacaoDTO salvar(VariacaoDTO variacaoDTO) {

        associarVariacao(variacaoDTO);
        Variacao variacao = mapper.dtoToEntity(variacaoDTO);
        return mapper.entityToDTO(repository.salvar(variacao));
    }

    public VariacaoDTO buscarPorId(Long id) {
        Variacao variacao = repository.buscarPorId(id);
        return mapper.entityToDTO(variacao);
    }

    public void delete(Long id) {

        repository.deletarPorId(id);
    }

    public PageResponse<VariacaoDTO> findPaginated(PageRequest pageable, VariacaoFilterRequest request) {

        Page<Variacao> variacaoPage = repository.buscarPorFiltroComPaginacao(pageable, request);
        PageResponse<Variacao> page = pageResponseMapper.pageToPageResponse(variacaoPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarVariacao(VariacaoDTO variacaoDTO) {
        if (variacaoDTO.getIdItem() != null) {
            ItemDTO itemDTO = itemService.buscarPorId(variacaoDTO.getIdItem());
            variacaoDTO.setItem(itemDTO);
            variacaoDTO.setIdItem(itemDTO.getIdItem());
        }
    }
}
