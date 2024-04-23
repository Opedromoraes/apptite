package com.dev.apptite.api.controller.restaurante;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestauranteController implements IRestauranteController {



    @Override
    public RestauranteResponse create(RestauranteRequest restauranteRequest) {
        return null;
    }

    @Override
    public RestauranteResponse findAll() {
        return null;
    }
}
