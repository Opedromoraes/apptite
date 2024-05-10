package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    Long idCategoria;

    String nome;

    Long idRestaurante;

    RestauranteDTO restaurante;
}
