package com.dev.apptite.service;

import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.RestauranteEntity;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final RestauranteRepository repository;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {

        List<String> errors = new ArrayList<>();

        if (restauranteDTO.getNome() == null){
            errors.add("O nome não pode ser nulo");
        }

        if (restauranteDTO.getEndereco() == null){
            errors.add("O endereço não pode ser nulo");
        }
        if (!errors.isEmpty()){
            throw new NotFoundException("base.message.error",errors);
        }

        RestauranteEntity restaurante = repository.save(mapper.dtoToEntity(restauranteDTO));
        return mapper.entityToDTO(restaurante);
    }

    public List<RestauranteDTO> findAll() {

        List<RestauranteEntity> restaurantes = repository.findAll();
        return mapper.entityToDTO(restaurantes);
    }

    public RestauranteDTO update(RestauranteUpdateRequest request, Long id) {

        RestauranteDTO restauranteDTO = findById(id);
        RestauranteDTO restauranteNovoDTO = mapper.requestUpdateToDto(request);
        BeanUtils.copyProperties(restauranteNovoDTO, restauranteDTO, "idRestaurante");

        RestauranteEntity restaurante = repository.save(mapper.dtoToEntity(restauranteDTO));

        return mapper.entityToDTO(restaurante);
    }

    public RestauranteDTO findById(Long id) {

        RestauranteEntity restaurante = repository.findById(id).orElseThrow();
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
