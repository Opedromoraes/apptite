package com.dev.apptite.api.controller.categoria.response;

import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    Long idCategoria;
    String nome;
    RestauranteResponse restaurante;

}
