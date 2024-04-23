package com.dev.apptite.api.controller.restaurante.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class RestauranteResponse {
    String nome;
}
