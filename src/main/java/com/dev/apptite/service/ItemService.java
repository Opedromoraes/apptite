package com.dev.apptite.service;

import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.entity.Item;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.ItemMapper;
import com.dev.apptite.repository.impl.IItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemMapper mapper;
    private final IItemRepository repository;

    public ItemDTO salvar(ItemDTO itemDTO) {

//        associarRestaurante(itemDTO);

        Item item = mapper.dtoToEntity(itemDTO);
        return mapper.entityToDTO(repository.save(item));
    }

    public List<ItemDTO> findAll() {
        List<Item> itens = repository.findAll();
        return mapper.entityToDTO(itens);
    }

    public ItemDTO findById(Long id) {
        Item Item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item não encontrada"));
        return mapper.entityToDTO(Item);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar. Item não encontrada para o ID: " + id);
        }
        repository.deleteById(id);
    }



//    public ItemDTO update(ItemDTO ItemNovaDTO, Long id) {
//
//        ItemDTO ItemDTO = findById(id);
//
//        BeanUtils.copyProperties(ItemNovaDTO, ItemDTO, "idItem");
//        Item Item = repository.save(mapper.dtoToEntity(ItemDTO));
//
//        return mapper.entityToDTO(Item);
//    }

}