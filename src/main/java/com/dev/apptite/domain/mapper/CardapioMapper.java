package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.cardapio.request.CardapioRequest;
import com.dev.apptite.api.controller.cardapio.response.CardapioResponse;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardapioMapper {

    CardapioDTO entityToDTO(Cardapio entity);

    Cardapio dtoToEntity(CardapioDTO dto);

    CardapioDTO requestToDto(CardapioRequest request);

    CardapioResponse dtoToResponse(CardapioDTO dto);
    PageResponse<CardapioDTO> mapPageEntityToPageDto(PageResponse<Cardapio> page);
    PageResponse<CardapioResponse> mapPageDtoToPageResponse(PageResponse<CardapioDTO> page);

}
