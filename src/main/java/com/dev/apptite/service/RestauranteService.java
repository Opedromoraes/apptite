package com.dev.apptite.service;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final RestauranteRepository repository;
    private final PageResponseMapper pageResponseMapper;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = mapper.dtoToEntity(restauranteDTO);
        return mapper.entityToDTO(repository.salvar(restaurante));
    }

    public RestauranteDTO update(RestauranteDTO restauranteNovoDTO, Long id) {
        Restaurante restaurante = mapper.dtoToEntity(restauranteNovoDTO);
        restaurante.setIdRestaurante(id);
        return mapper.entityToDTO(repository.salvar(restaurante));
    }

    public RestauranteDTO findById(Long id) {
        Restaurante restaurante = repository.buscarPorId(id);
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public PageResponse<RestauranteDTO> findPaginated(Pageable pageable, RestauranteFilterRequest request) {
        Page<Restaurante> restaurantePage = repository.buscarPorFiltroComPaginacao(pageable, request);
        PageResponse<Restaurante> page = pageResponseMapper.pageToPageResponse(restaurantePage);
        return mapper.mapPageEntityToPageDto(page);
    }
}
