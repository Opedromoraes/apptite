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

        RestauranteDTO restauranteDtoSalvo = service.salvar(mapper.requestToDto(restauranteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.dtoToResponse(restauranteDtoSalvo));
    }

    @Override
    public ResponseEntity<List<RestauranteResponse>> findAll() {

        List<RestauranteDTO> restaurantesDTO = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(restaurantesDTO));
    }

    @Override
    public ResponseEntity<RestauranteResponse> update(RestauranteUpdateRequest request, Long id) {

        RestauranteDTO restauranteDtoAtualizado = service.update(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(restauranteDtoAtualizado));
    }

    @Override
    public ResponseEntity<RestauranteResponse> findById(Long id) {
        RestauranteDTO restauranteDTO = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.dtoToResponse(restauranteDTO));

    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


    // criar um endpoint do tipo put onde vou receber um restaurante request no body e um id por query param
    // no controller chamar um service e no service chamar um repository.findbyid para recuperar o restaurante
    // fazer um mapper de restaurante request para dto
    // setar o id recebido no query param no dto
    // converter a dto(com id) para entity e salvar com no banco usando repository.save

}
