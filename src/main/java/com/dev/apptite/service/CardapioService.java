package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.repository.CardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class CardapioService {

    private final CardapioMapper mapper;
    private final CardapioRepository repository;
    private final CategoriaService categoriaService;

    public CardapioDTO salvar(CardapioDTO cardapioDTO) {

        List<CategoriaDTO> categorias = ofNullable(cardapioDTO.getCategorias()).orElse(List.of());

        if (!categorias.isEmpty()) {
            List<CategoriaDTO> categoriasId = categoriaService.findCategoriasId(cardapioDTO.getCategoriasId());
            cardapioDTO.setCategorias(categoriasId);
        }

        Cardapio cardapio = mapper.dtoToEntity(cardapioDTO);
        Cardapio cardapioSalvo = repository.save(cardapio);

        return mapper.entityToDTO(cardapioSalvo);
    }

}
