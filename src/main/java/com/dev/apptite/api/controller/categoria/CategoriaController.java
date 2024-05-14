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
    public ResponseEntity<CategoriaResponse> create(CategoriaRequest request) {
        CategoriaDTO categoriaDTO = service.salvar(mapper.requestToDto(request));
        CategoriaResponse response = mapper.dtoToResponse(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<CategoriaResponse>> findAll() {
        List<CategoriaDTO> restaurantesDTO = service.findAll();
        List<CategoriaResponse> response = mapper.dtoToResponse(restaurantesDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<CategoriaResponse> findById(Long id) {
        CategoriaDTO categoriaDTO = service.findById(id);
        CategoriaResponse response = mapper.dtoToResponse(categoriaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<List<CategoriaResponse>> findByIdRestaurante(Long idRestaurante) {
        List<CategoriaDTO> restauranteDTO = service.findByIdRestaurante(idRestaurante);
        List<CategoriaResponse> response = mapper.dtoToResponse(restauranteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
