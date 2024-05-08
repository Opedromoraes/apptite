package com.dev.apptite.service;

import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.repository.CardapioRepository;
import com.dev.apptite.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CardapioService {

    private final CardapioMapper mapper;
    private final CardapioRepository repository;

    public CardapioDTO salvar(CardapioDTO cardapioDTO) {


        Cardapio cardapio = repository.save(mapper.dtoToEntity(cardapioDTO));
        return mapper.entityToDTO(cardapio);
    }

}
