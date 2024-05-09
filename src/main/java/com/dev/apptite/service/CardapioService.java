package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.repository.CardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@AllArgsConstructor
public class CardapioService {

    private final CardapioMapper mapper;
    private final CardapioRepository repository;
    private final CategoriaService categoriaService;

    public CardapioDTO salvar(CardapioDTO cardapioDTO) {

        if (isNotEmpty(cardapioDTO.getCategorias())) {
            List<CategoriaDTO> categoriasDTO = categoriaService.findCategoriasId(cardapioDTO.getCategoriasId());
            cardapioDTO.setCategorias(categoriasDTO);
        }

        Cardapio cardapio = mapper.dtoToEntity(cardapioDTO);
        Cardapio cardapioSalvo = repository.save(cardapio);

        return mapper.entityToDTO(cardapioSalvo);
    }

    public CardapioDTO duplicar(CardapioDTO cardapioNovoDTO, Long idOriginal) {

        CardapioDTO cardapioOriginal = findById(idOriginal);

        if (isNotEmpty(cardapioNovoDTO.getCategoriasId())) {
            List<CategoriaDTO> categoriasId = categoriaService.findCategoriasId(cardapioNovoDTO.getCategoriasId());
            removeCategoriasDuplicadas(categoriasId, cardapioOriginal.getCategorias());
            cardapioOriginal.setCategorias(categoriasId);
        }

        Cardapio cardapio = mapper.dtoToEntity(cardapioOriginal);
        Cardapio cardapioSalvo = repository.save(cardapio);

        return mapper.entityToDTO(cardapioSalvo);
    }

    public CardapioDTO findById(Long idOriginal) {
        Cardapio cardapio = repository.findById(idOriginal)
                .orElseThrow(() -> new NotFoundException("Cardápio não encontrado"));
        return mapper.entityToDTO(cardapio);
    }

    private void removeCategoriasDuplicadas(List<CategoriaDTO> categoriasId, List<CategoriaDTO> categorias) {
        categoriasId.removeIf(categoriaId -> categorias.stream()
                .anyMatch(categoria -> categoria.getIdCategoria().equals(categoriaId.getIdCategoria())));
    }
}
