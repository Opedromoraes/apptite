package com.dev.apptite.api.controller.categoria.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoriaUpdateRequest {

    @Schema(description = "Nome do restaurante", example = "Churras")
    private String id;

    @Schema(description = "Endereço", example = "Avenida Miguel Perrela, 69")
    private String no;
}
