package com.dev.apptite.api.controller.pedido;

import com.dev.apptite.api.controller.pedido.request.PedidoFilterRequest;
import com.dev.apptite.api.controller.pedido.request.PedidoRequest;
import com.dev.apptite.api.controller.pedido.response.PedidoResponse;
import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.mapper.PedidoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class PedidoController implements IPedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;

//    @Override
//    public ResponseEntity<CardapioResponse> create(CardapioRequest request) {
//
//    }
//
//    @Override
//    public ResponseEntity<CardapioResponse> findById(Long id) {
//        CardapioDTO cardapioDTO = service.buscarPorId(id);
//        CardapioResponse response = mapper.dtoToResponse(cardapioDTO);
//        return ResponseEntity.status(OK).body(response);
//    }
//
//    @Override
//    public ResponseEntity<Void> delete(Long id) {
//        service.deletarPorId(id);
//        return ResponseEntity.status(NO_CONTENT).build();
//    }
//
//    @Override
//    public ResponseEntity<PageResponse<CardapioResponse>> findAllPaginated(int pageNumber, int pageSize, String nome, Long idRestaurante, String nomeRestaurante) {
//
//        CardapioFilterRequest request = CardapioFilterRequest.builder()
//                .nome(nome)
//                .idRestaurante(idRestaurante)
//                .nomeRestaurante(nomeRestaurante)
//                .build();
//
//        PageResponse<CardapioDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize), request);
//        PageResponse<CardapioResponse> response = mapper.mapPageDtoToPageResponse(paginated);
//
//        return ResponseEntity.status(OK).body(response);
//    }

    @Override
    public ResponseEntity<PedidoResponse> create(PedidoRequest request) {
        PedidoDTO pedidoDTO = service.salvar(mapper.requestToDto(request));
        PedidoResponse response = mapper.dtoToResponse(pedidoDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<PedidoResponse> findById(Long id) {
        PedidoDTO pedidoDTO = service.buscarPorId(id);
        PedidoResponse response = mapper.dtoToResponse(pedidoDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<PedidoResponse>> findAllPaginated(int pageNumber, int pageSize, String status) {
        PedidoFilterRequest request = PedidoFilterRequest.builder()
                .status(status)
                .build();

        PageResponse<PedidoDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize), request);
        PageResponse<PedidoResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
    }
}
