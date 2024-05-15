package com.dev.apptite.api.controller.cardapio.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioResponse {

    Long idCardapio;
    String nome;
    List<Long> idsCategoria;
    Long idRestaurante;

}