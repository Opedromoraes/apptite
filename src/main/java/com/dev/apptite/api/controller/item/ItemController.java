package com.dev.apptite.api.controller.item;

import com.dev.apptite.api.controller.item.request.ItemFilterRequest;
import com.dev.apptite.api.controller.item.request.ItemRequest;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.mapper.ItemMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class ItemController implements IItemController {

    private final ItemMapper mapper;
    private final ItemService service;


    @Override
    public ResponseEntity<ItemResponse> create(ItemRequest request) {
        ItemDTO itemDTO = service.salvar(mapper.requestToDto(request));
        ItemResponse response = mapper.dtoToResponse(itemDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<ItemResponse> findById(Long id) {
        ItemDTO itemDTO = service.findById(id);
        ItemResponse response = mapper.dtoToResponse(itemDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<ItemResponse>> findAllPaginated(int pageNumber, int pageSize, String nome, String descricao, double preco) {

        ItemFilterRequest filter = ItemFilterRequest.builder().nome(nome).build();

        PageResponse<ItemDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize), filter);
        PageResponse<ItemResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
    }

}

