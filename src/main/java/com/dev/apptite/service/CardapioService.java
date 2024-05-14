package com.dev.apptite.service;

import com.dev.apptite.domain.dto.CardapioDTO;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CardapioMapper;
import com.dev.apptite.repository.CardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardapioService {

    private final CardapioMapper mapper;
    private final CardapioRepository repository;
    private final RestauranteService restauranteService;
    private final CategoriaService categoriaService;

    public CardapioDTO salvar(CardapioDTO cardapioDTO) {

        validacaoCardapio(cardapioDTO);

        if (cardapioDTO.getCategoriasId() != null) {
            List<CategoriaDTO> categoriasDTO = categoriaService.findAllByIds(cardapioDTO.getCategoriasId());
            categoriasDTO.forEach(categoriaDTO -> categoriaDTO.setIdCardapio(cardapioDTO.getIdCardapio()));
            cardapioDTO.setCategorias(categoriasDTO);
        }

        RestauranteDTO restauranteDTO = restauranteService.findById(cardapioDTO.getIdRestaurante());
        cardapioDTO.setRestaurante(restauranteDTO);

        Cardapio cardapio = repository.save(mapper.dtoToEntity(cardapioDTO));

        return mapper.entityToDTO(cardapio);
    }

    public CardapioDTO findById(Long id) {
        Cardapio cardapio = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("base.message.error", List.of("menu.not-found.error")));
        return mapper.entityToDTO(cardapio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    private void validacaoCardapio(CardapioDTO cardapioDTO) {
        Optional<Cardapio> cardapioOptional = repository.findByNome(cardapioDTO.getNome());
        cardapioOptional.ifPresent(cardapio -> {
            throw new BusinessException("duplicate.menu.error");
        });
    }

    public CardapioDTO adicionarOuRemoverCategoria(Long id, Long idCategoria, Boolean isAdd) {

        CardapioDTO cardapioDTO = findById(id);
        CategoriaDTO categoriaDTO = categoriaService.findById(idCategoria);
        if (isAdd) {
            cardapioDTO.getCategoriasId().add(categoriaDTO.getIdCategoria());
        } else {
            cardapioDTO.getCategoriasId().remove(categoriaDTO.getIdCategoria());
        }
        return salvar(cardapioDTO);
    }
}
