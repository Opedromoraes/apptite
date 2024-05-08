package com.dev.apptite.service;

import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final RestauranteRepository repository;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = repository.save(mapper.dtoToEntity(restauranteDTO));
        return mapper.entityToDTO(restaurante);
    }

    public List<RestauranteDTO> findAll() {

        List<Restaurante> restaurantes = repository.findAll();
        return mapper.entityToDTO(restaurantes);
    }

    public RestauranteDTO update(RestauranteUpdateRequest request, Long id) {

        RestauranteDTO restauranteDTO = findById(id);
        RestauranteDTO restauranteNovoDTO = mapper.requestUpdateToDto(request);
        BeanUtils.copyProperties(restauranteNovoDTO, restauranteDTO, "idRestaurante");

        Restaurante restaurante = repository.save(mapper.dtoToEntity(restauranteDTO));

        return mapper.entityToDTO(restaurante);
    }

    public RestauranteDTO findById(Long id) {

        Restaurante restaurante = repository.findById(id).orElseThrow();
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
