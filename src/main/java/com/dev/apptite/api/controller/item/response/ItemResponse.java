package com.dev.apptite.api.controller.item.response;

import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    Long idCategoria;
    String nome;
    RestauranteResponse restaurante;

}
