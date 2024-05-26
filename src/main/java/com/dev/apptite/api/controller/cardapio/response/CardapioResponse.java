package com.dev.apptite.api.controller.cardapio.response;

import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponse {

    Long idCardapio;
    String nome;
    RestauranteResponse restaurante;

}