package com.dev.apptite.api.controller.categoria.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    Long idCategoria;
    String nome;

}
