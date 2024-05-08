package com.dev.apptite.api.controller.cardapio;

import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.service.CardapioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardapioController implements ICardapioController {

    private final CardapioService service;
    private final CategoriaMapper mapper;

    @Override
    public ResponseEntity<CardapioResponse> create(CardapioRequest cardapioRequest) {
        RestauranteDTO restauranteDtoSalvo = service.salvar(mapper.requestToDto(cardapioRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.dtoToResponse(restauranteDtoSalvo));
    }

    @Override
    public ResponseEntity<CardapioResponse> duplicateMenu(CardapioRequest cardapioRequest, Long id) {
        return null;
    }
}
