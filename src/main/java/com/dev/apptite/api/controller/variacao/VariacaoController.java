package com.dev.apptite.api.controller.variacao;

import com.dev.apptite.api.controller.variacao.request.VariacaoFilterRequest;
import com.dev.apptite.api.controller.variacao.request.VariacaoRequest;
import com.dev.apptite.api.controller.variacao.response.VariacaoResponse;
import com.dev.apptite.domain.dto.VariacaoDTO;
import com.dev.apptite.domain.mapper.VariacaoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.service.VariacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class VariacaoController implements IVariacaoController {

    private final VariacaoMapper mapper;
    private final VariacaoService service;


    @Override
    public ResponseEntity<VariacaoResponse> create(VariacaoRequest request) {
        VariacaoDTO variacaoDTO = service.salvar(mapper.requestToDto(request));
        VariacaoResponse response = mapper.dtoToResponse(variacaoDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<VariacaoResponse> findById(Long id) {
        VariacaoDTO variacaoDTO = service.buscarPorId(id);
        VariacaoResponse response = mapper.dtoToResponse(variacaoDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<VariacaoResponse>> findAllPaginated(int pageNumber, int pageSize, String nome, Long idItem) {

        VariacaoFilterRequest filter = VariacaoFilterRequest.builder().nome(nome).idItem(idItem).build();


        PageResponse<VariacaoDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize), filter);
        PageResponse<VariacaoResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
    }

}

