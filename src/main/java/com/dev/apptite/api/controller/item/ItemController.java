package com.dev.apptite.api.controller.item;

import com.dev.apptite.api.controller.item.request.ItemRequest;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.mapper.ItemMapper;
import com.dev.apptite.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController implements IItemController {

    private final ItemMapper mapper;
    private final ItemService service;


    @Override
    public ResponseEntity<ItemResponse> create(ItemRequest request) {
        ItemDTO itemDTO = service.salvar(mapper.requestToDto(request));
        ItemResponse response = mapper.dtoToResponse(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<ItemResponse>> findAll() {
        List<ItemDTO> itemDTO = service.findAll();
        List<ItemResponse> response = mapper.dtoToResponse(itemDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<ItemResponse> findById(Long id) {
        ItemDTO itemDTO = service.findById(id);
        ItemResponse response = mapper.dtoToResponse(itemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

