package com.dev.apptite.service;

import com.dev.apptite.api.controller.categoria.request.CategoriaFilterRequest;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.CategoriaRepository;
import com.dev.apptite.repository.impl.ICategoriaRepository;
import com.dev.apptite.domain.exceptions.BusinessException;
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
    private final ICategoriaRepository iRepository;
    private final CategoriaRepository repository;
    private final PageResponseMapper pageResponseMapper;

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {

        Categoria categoria = mapper.dtoToEntity(categoriaDTO);

        return mapper.entityToDTO(iRepository.save(categoria));
    }

    public CategoriaDTO update(CategoriaDTO categoriaNovaDTO, Long id) {

        CategoriaDTO categoriaDTO = findById(id);

        BeanUtils.copyProperties(categoriaNovaDTO, categoriaDTO, "idCategoria");
        Categoria categoria = iRepository.save(mapper.dtoToEntity(categoriaDTO));

        return mapper.entityToDTO(categoria);
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = iRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        return mapper.entityToDTO(categoria);
    }

    public Categoria findByIdEntity(Long id) {
        return iRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }

    public void delete(Long id) {
        if (!iRepository.existsById(id)) {
            throw new BusinessException("Não é possível deletar. Categoria não encontrada para o ID: " + id);
        }
        iRepository.deleteById(id);
    }

    public List<CategoriaDTO> findByIdRestaurante(Long idRestaurante) {
        List<Categoria> categorias = iRepository.findByIdRestaurante(idRestaurante);
        return mapper.entityToDTO(categorias);
    }


    public PageResponse<CategoriaDTO> findPaginated(PageRequest pageable, CategoriaFilterRequest request) {

        Page<Categoria> categoriaPage = repository.findPaginated(pageable, request);
        PageResponse<Categoria> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);

    }
}
