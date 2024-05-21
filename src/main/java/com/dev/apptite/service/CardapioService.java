package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.repository.impl.ICardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CardapioService {

    private final CardapioMapper mapper;
    private final ICardapioRepository repository;
    private final RestauranteService restauranteService;

    public CardapioDTO salvar(CardapioDTO cardapioDTO) {

        associarRestaurante(cardapioDTO);

        Cardapio cardapio = mapper.dtoToEntity(cardapioDTO);

        Cardapio save = repository.save(cardapio);
        return mapper.entityToDTO(save);
    }

    public CardapioDTO update(CardapioDTO cardapioNovoDTO, Long id) {

        CardapioDTO cardapioDTO = findById(id);
        BeanUtils.copyProperties(cardapioNovoDTO, cardapioDTO, "idCardapio");
        Cardapio cardapio = mapper.dtoToEntity(cardapioDTO);

        return mapper.entityToDTO(repository.save(cardapio));
    }

    public CardapioDTO findById(Long id) {
        Cardapio cardapio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("base.message.error", List.of("menu.not-found.error")));
        return mapper.entityToDTO(cardapio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void associarRestaurante(CardapioDTO cardapioDTO) {
        if (cardapioDTO.getIdRestaurante() != null) {
            RestauranteDTO restauranteDTO = restauranteService.findById(cardapioDTO.getIdRestaurante());
            cardapioDTO.setRestaurante(restauranteDTO);
            cardapioDTO.setIdRestaurante(restauranteDTO.getIdRestaurante());
        }
    }

}
