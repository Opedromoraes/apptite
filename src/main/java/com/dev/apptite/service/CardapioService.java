package com.dev.apptite.service;

import com.dev.apptite.api.controller.cardapio.request.CardapioFilterRequest;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.CardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardapioService {

    private final CardapioMapper mapper;
    private final CardapioRepository repository;
    private final RestauranteService restauranteService;
    private final PageResponseMapper pageResponseMapper;

    public CardapioDTO salvar(CardapioDTO cardapioDTO) {

        associarRestaurante(cardapioDTO);
        Cardapio cardapio = mapper.dtoToEntity(cardapioDTO);
        Cardapio save = repository.salvar(cardapio);

        return mapper.entityToDTO(save);
    }

    public CardapioDTO atualizar(CardapioDTO cardapioNovoDTO, Long id) {

        CardapioDTO cardapioDTO = buscarPorId(id);
        BeanUtils.copyProperties(cardapioNovoDTO, cardapioDTO, "idCardapio");
        Cardapio cardapio = mapper.dtoToEntity(cardapioDTO);

        return mapper.entityToDTO(repository.salvar(cardapio));
    }

    public CardapioDTO buscarPorId(Long id) {
        Cardapio cardapio = repository.buscarPorId(id);
        return mapper.entityToDTO(cardapio);
    }

    public void deletarPorId(Long id) {
        repository.deletarPorId(id);
    }

    private void associarRestaurante(CardapioDTO cardapioDTO) {
        if (cardapioDTO.getIdRestaurante() != null) {
            RestauranteDTO restauranteDTO = restauranteService.findById(cardapioDTO.getIdRestaurante());
            cardapioDTO.setRestaurante(restauranteDTO);
            cardapioDTO.setIdRestaurante(restauranteDTO.getIdRestaurante());
        }
    }

    public PageResponse<CardapioDTO> findPaginated(PageRequest pageable, CardapioFilterRequest request) {

        Page<Cardapio> cardapioPage = repository.buscarPorFiltroComPaginacao(pageable, request);
        PageResponse<Cardapio> page = pageResponseMapper.pageToPageResponse(cardapioPage);
        return mapper.mapPageEntityToPageDto(page);
    }
}
