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
    private CardapioDTO cardapio;
    private List<CategoriaDTO> categorias;
    private List<Long> idsCategoria;
    private List<Long> idsCardapio;

}
