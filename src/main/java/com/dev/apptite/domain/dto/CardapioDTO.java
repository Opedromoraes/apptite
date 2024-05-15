package com.dev.apptite.domain.dto;

import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioDTO {

    Long idCardapio;
    String nome;
    List<CategoriaDTO> categorias;
    List<Long> idsCategoria;
    Long idRestaurante;
    RestauranteDTO restaurante;
}
