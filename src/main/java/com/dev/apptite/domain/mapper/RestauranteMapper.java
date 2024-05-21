package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteDTO entityToDTO(Restaurante entity);
    Restaurante dtoToEntity(RestauranteDTO dto);
    RestauranteResponse dtoToResponse(RestauranteDTO dto);
    RestauranteDTO requestToDto(RestauranteRequest request);
    RestauranteDTO requestUpdateToDto(RestauranteUpdateRequest request);

    List<RestauranteDTO> entityToDTO(List<Restaurante> entity);
    List<RestauranteResponse> dtoToResponse(List<RestauranteDTO> dto);

    default Page<RestauranteDTO> entityToDTO(Page<Restaurante> entityPage) {
        List<RestauranteDTO> dtoList = entityToDTO(entityPage.getContent());
        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    default Page<RestauranteResponse> dtoToPageResponse(Page<RestauranteDTO> dtoPage) {
        List<RestauranteResponse> responseList = dtoToResponse(dtoPage.getContent());
        return new PageImpl<>(responseList, dtoPage.getPageable(), dtoPage.getTotalElements());
    }
}
