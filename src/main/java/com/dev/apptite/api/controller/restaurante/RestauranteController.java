package com.dev.apptite.api.controller.restaurante;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestauranteController implements IRestauranteController {

    private final RestauranteMapper mapper;
    private final RestauranteService service;

    @Override
    public ResponseEntity<RestauranteResponse> create(RestauranteRequest restauranteRequest) {
        RestauranteDTO response = service.salvar(mapper.requestToDto(restauranteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<List<RestauranteResponse>> findAll() {
        List<RestauranteDTO> response = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<RestauranteResponse> update(RestauranteUpdateRequest request, Long id) {
        RestauranteDTO response = service.update(mapper.requestUpdateToDto(request), id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<RestauranteResponse> findById(Long id) {
        RestauranteDTO response = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(response));

    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
