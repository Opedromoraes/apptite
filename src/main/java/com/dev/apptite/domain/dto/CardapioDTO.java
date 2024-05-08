package com.dev.apptite.domain.dto;

import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import lombok.*;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CardapioDTO {

    Long idCardapio;
    String nome;
    Set<CategoriaDTO> categorias;
    Set<Long> categoriasId;
}
