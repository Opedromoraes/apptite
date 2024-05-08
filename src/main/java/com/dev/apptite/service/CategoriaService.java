package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class CategoriaService {

    private final CategoriaMapper mapper;
    private final CategoriaRepository repository;

    public CategoriaDTO salvar(CategoriaDTO restauranteDTO) {
        Categoria restaurante = repository.save(mapper.dtoToEntity(restauranteDTO));
        return mapper.entityToDTO(restaurante);
    }

    public List<CategoriaDTO> findAll() {

        List<Categoria> restaurantes = repository.findAll();
        return mapper.entityToDTO(restaurantes);
    }

//    public CategoriaDTO update(CategoriaUpdateRequest request, Long id) {
//
//        CategoriaDTO restauranteDTO = findById(id);
//        CategoriaDTO restauranteNovoDTO = mapper.requestUpdateToDto(request);
//        BeanUtils.copyProperties(restauranteNovoDTO, restauranteDTO, "idCategoria");
//
//        Categoria restaurante = repository.save(mapper.dtoToEntity(restauranteDTO));
//
//        return mapper.entityToDTO(restaurante);
//    }

    public CategoriaDTO findById(Long id) {

        Categoria restaurante = repository.findById(id).orElseThrow();
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
