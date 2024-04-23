package com.dev.apptite.domain.dto;

import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class FuncionarioDTO {
    Long idFuncionario;
    String nome;
    Long restauranteId;

}
