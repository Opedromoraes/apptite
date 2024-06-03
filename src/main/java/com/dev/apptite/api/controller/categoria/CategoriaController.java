package com.dev.apptite.api.controller.categoria;

import com.dev.apptite.api.controller.categoria.request.CategoriaFilterRequest;
import com.dev.apptite.api.controller.categoria.request.CategoriaRequest;
import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class CategoriaController implements ICategoriaController {

    private final CategoriaMapper mapper;
    private final CategoriaService service;

    @Override
    public ResponseEntity<CategoriaResponse> create(CategoriaRequest request) {
        CategoriaDTO categoriaDTO = service.salvar(mapper.requestToDto(request));
        CategoriaResponse response = mapper.dtoToResponse(categoriaDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<CategoriaResponse> findById(Long id) {
        CategoriaDTO categoriaDTO = service.findById(id);
        CategoriaResponse response = mapper.dtoToResponse(categoriaDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<List<CategoriaResponse>> findByIdRestaurante(Long idRestaurante) {
        List<CategoriaDTO> restauranteDTO = service.findByIdRestaurante(idRestaurante);
        List<CategoriaResponse> response = mapper.dtoToResponse(restauranteDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<CategoriaResponse>> findAllPaginated(int pageNumber, int pageSize, String nome) {

        CategoriaFilterRequest filter = CategoriaFilterRequest.builder().nome(nome).build();
        PageResponse<CategoriaDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize), filter);
        PageResponse<CategoriaResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
    }
}
