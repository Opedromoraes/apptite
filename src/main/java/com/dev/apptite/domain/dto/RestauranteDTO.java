package com.dev.apptite.domain.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    private Long idRestaurante;
    private String nome;
    private String endereco;
    private List<CardapioDTO> cardapios;
    private List<CategoriaDTO> categorias;
    private List<Long> idsCategoria;
    private List<Long> idsCardapio;

}
