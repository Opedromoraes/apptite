package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {
    RestauranteMapper INSTANCE = Mappers.getMapper( RestauranteMapper.class );

    RestauranteDTO entityToDTO (RestauranteEntity entity);
    RestauranteEntity dtoToEntity(RestauranteDTO dto);
    RestauranteResponse dtoToResponse(RestauranteDTO dto);
    RestauranteDTO requestToDto(RestauranteRequest request);
    RestauranteDTO requestUpdateToDto(RestauranteUpdateRequest request);


    List<RestauranteDTO> entityToDTO (List<RestauranteEntity> entity);
    List<RestauranteResponse> dtoToResponse(List<RestauranteDTO> dto);


}
