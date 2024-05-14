package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaMapper mapper;
    private final CategoriaRepository repository;
    private final RestauranteService restauranteService;

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        validacaoCategoria(categoriaDTO);
        RestauranteDTO restauranteDTO = restauranteService.findById(categoriaDTO.getIdRestaurante());
        categoriaDTO.setRestaurante(restauranteDTO);
        Categoria categoria = repository.save(mapper.dtoToEntity(categoriaDTO));
        return mapper.entityToDTO(categoria);
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> restaurantes = repository.findAll();
        return mapper.entityToDTO(restaurantes);
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow();
        return mapper.entityToDTO(categoria);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<CategoriaDTO> findByIdRestaurante(Long idRestaurante) {
        List<Categoria> categorias = repository.findCategoriasByIdRestaurante(idRestaurante);
        return mapper.entityToDTO(categorias);
    }

    public List<CategoriaDTO> findAllByIds(List<Long> ids) {
        List<Categoria> categorias = repository.findByIds(ids);
        if (categorias.isEmpty()){
            throw new NotFoundException("base.message.error",List.of("category.not-found.error"));
        }
        return mapper.entityToDTO(categorias);
    }

    private void validacaoCategoria(CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaOptional = repository.findByNome(categoriaDTO.getNome());
        categoriaOptional.ifPresent(categoria ->{
            throw new BusinessException("duplicate.category.error");
        });
    }
}
