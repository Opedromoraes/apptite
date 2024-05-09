package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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

    public List<CategoriaDTO> findCategoriasId(List<Long> categoriasId) {
        List<Categoria> categorias = repository.findCategoriasId(categoriasId);
        return mapper.entityToDTO(categorias);
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow();
        return mapper.entityToDTO(categoria);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
