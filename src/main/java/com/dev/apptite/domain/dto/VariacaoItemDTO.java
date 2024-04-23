package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class VariacaoItemDTO {
    Long idVariacao;
    String tipo;
    Double custoAdicional;

}
