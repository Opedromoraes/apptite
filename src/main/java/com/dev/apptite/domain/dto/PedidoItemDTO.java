package com.dev.apptite.domain.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PedidoItemDTO {
    Long idPedidoItem;
    MenuItemDTO menuItemDTO;
    int quantidade;
    List<VariacaoItemDTO> variacoes;
}
