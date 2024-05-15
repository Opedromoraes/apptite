package com.dev.apptite.api.controller.cardapio;

import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.service.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardapioController implements ICardapioController {

    private final CardapioService service;
    private final CardapioMapper mapper;

    @Override
    public ResponseEntity<CardapioResponse> create(CardapioRequest request) {
        CardapioDTO cardapioDTO = service.salvar(mapper.requestToDto(request));
        CardapioResponse response = mapper.dtoToResponse(cardapioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<CardapioResponse> findById(Long id) {
        CardapioDTO cardapioDTO = service.findById(id);
        CardapioResponse response = mapper.dtoToResponse(cardapioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<CardapioResponse> addOrRemoveCategory(Long id, Long idCategoria, Boolean isAdd) {
        CardapioDTO cardapioDTO = service.adicionarOuRemoverCategoria(id, idCategoria, isAdd);
        CardapioResponse response = mapper.dtoToResponse(cardapioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
