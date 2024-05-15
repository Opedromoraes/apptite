package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long idCategoria;
    private String nome;
    private Long idRestaurante;
    private RestauranteDTO restaurante;
    private Long idCardapio;

}
