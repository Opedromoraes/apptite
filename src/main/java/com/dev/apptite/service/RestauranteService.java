package com.dev.apptite.service;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.RestauranteRepository;
import com.dev.apptite.repository.impl.IRestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final IRestauranteRepository repositoryImpl;
    private final RestauranteRepository repository;
    private final PageResponseMapper pageResponseMapper;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = mapper.dtoToEntity(restauranteDTO);
        return mapper.entityToDTO(repositoryImpl.save(restaurante));
    }

    public List<RestauranteDTO> findAll() {
        List<Restaurante> restaurantes = repositoryImpl.findAll();
        return mapper.entityToDTO(restaurantes);
    }

    public RestauranteDTO update(RestauranteDTO restauranteNovoDTO, Long id) {

        RestauranteDTO restauranteDTO = findById(id);
        BeanUtils.copyProperties(restauranteNovoDTO, restauranteDTO, "idRestaurante");

        Restaurante restaurante = mapper.dtoToEntity(restauranteDTO);
        return mapper.entityToDTO(repositoryImpl.save(restaurante));
    }

    public RestauranteDTO findById(Long id) {
        Restaurante restaurante = repositoryImpl.findById(id).orElseThrow();
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        repositoryImpl.deleteById(id);
    }

    public PageResponse<RestauranteDTO> findPaginated(Pageable pageable, RestauranteFilterRequest request) {

        Page<Restaurante> restaurantePage = repository.findPaginated(pageable, request);
        PageResponse<Restaurante> page = pageResponseMapper.pageToPageResponse(restaurantePage);
        return mapper.mapPageEntityToPageDto(page);
    }
}
