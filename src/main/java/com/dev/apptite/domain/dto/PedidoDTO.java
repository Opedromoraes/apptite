package com.dev.apptite.domain.dto;

import com.dev.apptite.domain.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long idPedido;
    private StatusPedidoEnum status;
    private List<Long> idsItens;
    private List<ItemDTO> itens;

}
