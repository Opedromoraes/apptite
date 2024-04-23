package com.dev.apptite.domain.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class MenuItemDTO {
    Long idMenuItem;
    List<VariacaoItemDTO> variacoes;
    String nome;
    String descricao;
    Double preco;
}
