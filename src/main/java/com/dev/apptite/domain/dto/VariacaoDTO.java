package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariacaoDTO {

    private Long idVariacao;
    private String nome;
    private ItemDTO item;
    private Double preco;
    private Long idItem;
}
