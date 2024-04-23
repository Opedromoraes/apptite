package com.dev.apptite.api.controller.restaurante.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class RestauranteRequest {
    String nome;

}
