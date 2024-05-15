package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.api.controller.item.request.ItemRequest;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = CategoriaMapper.class, componentModel = "spring")
public interface ItemMapper {

    ItemDTO entityToDTO(Item entity);

    List<ItemDTO> entityToDTO(List<Item> entity);

    Item dtoToEntity(ItemDTO dto);

    ItemDTO requestToDto(ItemRequest request);

    List<ItemResponse> dtoToResponse(List<ItemDTO> dto);

    ItemResponse dtoToResponse(ItemDTO dto);



}
