package com.dev.apptite.service;

import com.dev.apptite.api.controller.item.request.ItemFilterRequest;
import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.entity.Item;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.ItemMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IItemRepository;
import com.dev.apptite.repository.impl.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemMapper mapper;
    private final ItemRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final CategoriaService categoriaService;

    public ItemDTO salvar(ItemDTO itemDTO) {

        associarCategoria(itemDTO);
        Item item = mapper.dtoToEntity(itemDTO);
        return mapper.entityToDTO(repository.salvar(item));
    }

    public ItemDTO findById(Long id) {
        Item item = repository.buscarPorId(id);
        return mapper.entityToDTO(item);
    }

    public void delete(Long id) {

        repository.deletarPorId(id);
    }

    public PageResponse<ItemDTO> findPaginated(PageRequest pageable, ItemFilterRequest request) {

        Page<Item> itemPage = repository.buscarPorFiltroComPaginacao(pageable, request);
        PageResponse<Item> page = pageResponseMapper.pageToPageResponse(itemPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarCategoria(ItemDTO itemDTO) {
        if (itemDTO.getIdCategoria() != null) {
            CategoriaDTO categoriaDTO = categoriaService.buscarPorId(itemDTO.getIdCategoria());
            itemDTO.setCategoria(categoriaDTO);
            itemDTO.setIdCategoria(categoriaDTO.getIdCategoria());
        }
    }
}
