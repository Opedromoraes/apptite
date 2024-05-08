package com.dev.apptite.api.controller.cardapio.response;

import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CardapioResponse {

    Long idCardapio;
    String nome;
    Set<CategoriaResponse> categorias;

}
