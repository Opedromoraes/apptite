package com.dev.apptite.api.controller.restaurante;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestauranteController implements IRestauranteController {

    private final RestauranteMapper mapper;
    private final RestauranteService service;

    @Override
    public ResponseEntity<RestauranteResponse> create(RestauranteRequest restauranteRequest) {

        RestauranteDTO restauranteDTO = mapper.requestToDto(restauranteRequest);
        RestauranteDTO restauranteDtoSalvo = service.salvar(restauranteDTO);
        RestauranteResponse restauranteResponse = mapper.dtoToResponse(restauranteDtoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteResponse);
    }

    @Override
    public RestauranteResponse findAll() {

        return null;
    }
}
