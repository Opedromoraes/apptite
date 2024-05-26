package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioDTO {

    Long idCardapio;
    String nome;
    Long idRestaurante;
    RestauranteDTO restaurante;
}
