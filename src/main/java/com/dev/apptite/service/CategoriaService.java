package com.dev.apptite.service;

import com.dev.apptite.api.controller.categoria.request.CategoriaFilterRequest;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaMapper mapper;
    private final CategoriaRepository repository;
    private final PageResponseMapper pageResponseMapper;

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.dtoToEntity(categoriaDTO);
        return mapper.entityToDTO(repository.salvar(categoria));
    }

    public CategoriaDTO update(CategoriaDTO categoriaNovaDTO, Long id) {

        CategoriaDTO categoriaDTO = findById(id);
        BeanUtils.copyProperties(categoriaNovaDTO, categoriaDTO, "idCategoria");
        Categoria categoria = repository.salvar(mapper.dtoToEntity(categoriaDTO));

        return mapper.entityToDTO(categoria);
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = repository.findById(id, Categoria.class)
                .orElseThrow(() -> new NotFoundException("Categoria de id <{}> não encontrada", id));
        return mapper.entityToDTO(categoria);
    }

    public Categoria findByIdEntity(Long id) {
        return repository.findById(id, Categoria.class)
                .orElseThrow(() -> new NotFoundException("Categoria de id  <{}> não encontrada ", id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Não é possível deletar. Categoria de id <{}> não encontrada para o ID: ", id);
        }
        repository.deletarPorId(id);
    }

    public List<CategoriaDTO> findByIdRestaurante(Long idRestaurante) {
        List<Categoria> categorias = repository.buscarPorIdRestaurante(idRestaurante);
        return mapper.entityToDTO(categorias);
    }


    public PageResponse<CategoriaDTO> findPaginated(PageRequest pageable, CategoriaFilterRequest request) {
        Page<Categoria> categoriaPage = repository.findPaginated(pageable, request, Categoria.class);
        PageResponse<Categoria> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);

    }
}
