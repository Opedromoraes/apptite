package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long idItem;
    private String nome;
    private Long idCategoria;
    private CategoriaDTO categoria;

}
