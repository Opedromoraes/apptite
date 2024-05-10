package com.dev.apptite.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {
    Long idRestaurante;
    String nome;
    String endereco;
}
