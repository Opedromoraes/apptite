package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.RestauranteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {
    RestauranteEntity dtoToEntity(RestauranteDTO dto);
    RestauranteResponse dtoToResponse(RestauranteDTO dto);
    RestauranteDTO requestToDto(RestauranteRequest request);

}
