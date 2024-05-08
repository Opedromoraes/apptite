package com.dev.apptite.api.controller.categoria;

import com.dev.apptite.api.controller.categoria.request.CategoriaRequest;
import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriaController implements ICategoriaController {

    private final CategoriaMapper mapper;
    private final CategoriaService service;

    @Override
    public ResponseEntity<CategoriaResponse> create(CategoriaRequest restauranteRequest) {

        CategoriaDTO restauranteDtoSalvo = service.salvar(mapper.requestToDto(restauranteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.dtoToResponse(restauranteDtoSalvo));
    }

    @Override
    public ResponseEntity<List<CategoriaResponse>> findAll() {

        List<CategoriaDTO> restaurantesDTO = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(restaurantesDTO));
    }

//    @Override
//    public ResponseEntity<CategoriaResponse> update(CategoriaUpdateRequest request, Long id) {
//
//        CategoriaDTO restauranteDtoAtualizado = service.update(request, id);
//        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(restauranteDtoAtualizado));
//    }

    @Override
    public ResponseEntity<CategoriaResponse> findById(Long id) {
        CategoriaDTO restauranteDTO = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(restauranteDTO));

    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
