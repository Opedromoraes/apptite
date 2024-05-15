package com.dev.apptite.api.controller.cardapio.response;

import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponse {

    Long idCardapio;
    String nome;
    List<CategoriaResponse> categorias;
    RestauranteResponse restaurante;

}