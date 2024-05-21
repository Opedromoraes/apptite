package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.repository.impl.ICategoriaRepository;
import com.dev.apptite.domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaMapper mapper;
    private final ICategoriaRepository repository;
    private final CardapioService cardapioService;

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {

        associarCardapio(categoriaDTO);
        Categoria categoria = mapper.dtoToEntity(categoriaDTO);

        return mapper.entityToDTO(repository.save(categoria));
    }

    public List<CategoriaDTO> findAll() {

        List<Categoria> categorias = repository.findAll();
        return mapper.entityToDTO(categorias);
    }

    public CategoriaDTO update(CategoriaDTO categoriaNovaDTO, Long id) {

        CategoriaDTO categoriaDTO = findById(id);

        BeanUtils.copyProperties(categoriaNovaDTO, categoriaDTO, "idCategoria");
        Categoria categoria = repository.save(mapper.dtoToEntity(categoriaDTO));

        return mapper.entityToDTO(categoria);
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        return mapper.entityToDTO(categoria);
    }

    public Categoria findByIdEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar. Categoria não encontrada para o ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<CategoriaDTO> findByIdRestaurante(Long idRestaurante) {
        List<Categoria> categorias = repository.findByIdRestaurante(idRestaurante);
        return mapper.entityToDTO(categorias);
    }

    private void associarCardapio(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getIdCardapio() != null) {
            CardapioDTO cardapioDTO = cardapioService.findById(categoriaDTO.getIdCardapio());
            categoriaDTO.setCardapio(cardapioDTO);
        }
    }
}
