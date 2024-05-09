package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.entity.Cardapio;
import org.mapstruct.Mapper;

@Mapper(uses = CategoriaMapper.class, componentModel = "spring")
public interface CardapioMapper {

    CardapioDTO entityToDTO(Cardapio entity);

    Cardapio dtoToEntity(CardapioDTO dto);

    CardapioDTO requestToDto(CardapioRequest request);

    CardapioResponse dtoToResponse(CardapioDTO dto);


}
