package com.dev.apptite.domain.dto;

import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CardapioDTO {

    Long idCardapio;
    String nome;
    List<CategoriaDTO> categorias;
    List<Long> categoriasId;
}
