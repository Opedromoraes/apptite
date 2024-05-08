package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardapioMapper {
    CardapioMapper INSTANCE = Mappers.getMapper( CardapioMapper.class );

    CardapioDTO entityToDTO (Cardapio entity);
    Cardapio dtoToEntity(CardapioDTO dto);
    CardapioResponse dtoToResponse(CardapioDTO dto);
    CardapioDTO requestToDto(CardapioRequest request);


    List<CardapioDTO> entityToDTO (List<Cardapio> entity);
    List<CardapioResponse> dtoToResponse(List<CardapioDTO> dto);


}
