package com.dev.apptite.api.controller.cardapio;

import com.dev.apptite.api.controller.cardapio.request.CardapioFilterRequest;
import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.service.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class CardapioController implements ICardapioController {

    private final CardapioService service;
    private final CardapioMapper mapper;

    @Override
    public ResponseEntity<CardapioResponse> create(CardapioRequest request) {
        CardapioDTO cardapioDTO = service.salvar(mapper.requestToDto(request));
        CardapioResponse response = mapper.dtoToResponse(cardapioDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<CardapioResponse> findById(Long id) {
        CardapioDTO cardapioDTO = service.buscarPorId(id);
        CardapioResponse response = mapper.dtoToResponse(cardapioDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<CardapioResponse>> findAllPaginated(int pageNumber, int pageSize, String nome, Long idRestaurante) {

        CardapioFilterRequest filter = CardapioFilterRequest.builder().nome(nome).build();


        PageResponse<CardapioDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize), filter);
        PageResponse<CardapioResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
  }
}
