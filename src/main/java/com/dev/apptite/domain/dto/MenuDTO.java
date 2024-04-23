package com.dev.apptite.domain.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class MenuDTO {
    Long idMenu;
    List<MenuItemDTO> itens;
    Long idRestaurante;
}
