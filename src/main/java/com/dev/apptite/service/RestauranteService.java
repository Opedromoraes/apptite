package com.dev.apptite.service;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.repository.impl.IRestauranteRepository;
import lombok.AllArgsConstructor;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final IRestauranteRepository repository;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = mapper.dtoToEntity(restauranteDTO);
        return mapper.entityToDTO(repository.save(restaurante));
    }

    public List<RestauranteDTO> findAll() {
        List<Restaurante> restaurantes = repository.findAll();
        return mapper.entityToDTO(restaurantes);
    }

    public RestauranteDTO update(RestauranteDTO restauranteNovoDTO, Long id) {

        RestauranteDTO restauranteDTO = findById(id);
        BeanUtils.copyProperties(restauranteNovoDTO, restauranteDTO, "idRestaurante");

        Restaurante restaurante = mapper.dtoToEntity(restauranteDTO);
        return mapper.entityToDTO(repository.save(restaurante));
    }

    public RestauranteDTO findById(Long id) {
        Restaurante restaurante = repository.findById(id).orElseThrow();
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void findPaginated(Pageable page, RestauranteFilterRequest request) {
    }
}
