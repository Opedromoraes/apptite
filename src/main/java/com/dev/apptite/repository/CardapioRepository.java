package com.dev.apptite.repository;

import com.dev.apptite.api.controller.cardapio.request.CardapioFilterRequest;
import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.exceptions.DataBaseException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.repository.criteria.CriteriaRepository;
import com.dev.apptite.repository.impl.ICardapioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dev.apptite.domain.enums.CriteriaTypeEnum.EQUAL;
import static com.dev.apptite.domain.enums.CriteriaTypeEnum.LIKE;

@Repository
@AllArgsConstructor
public class CardapioRepository extends CriteriaRepository<Cardapio> {

    private final ICardapioRepository repository;

    @Transactional(readOnly = true)
    public Page<Cardapio> buscarPorFiltroComPaginacao(@NonNull final Pageable pageable,
                                                      @NonNull final CardapioFilterRequest request) {
        return findPaginated(pageable, request, Cardapio.class);
    }

    @Transactional(readOnly = true)
    public Optional<Cardapio> buscarPorIdOptional(@NonNull final Long id) {
        return findById(id, Cardapio.class);
    }

    @Transactional(readOnly = true)
    public Cardapio buscarPorId(@NonNull Long id) {
        return findById(id, Cardapio.class)
                .orElseThrow(() -> new NotFoundException("Cardapio não encontrado: " + id));
    }

    @Transactional
    public Cardapio salvar(@NonNull Cardapio cardapio) {
        try {
            return save(cardapio);
        } catch (Exception e) {
            throw new DataBaseException("Erro ao salvar cardapio", e);
        }
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Cardapio> root, Object filterRequest) {

        CardapioFilterRequest request = (CardapioFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, Cardapio.Fields.nome, request.getNome(), LIKE);

        if (request.getIdRestaurante() != null || request.getNomeRestaurante() != null) {
            Join<Cardapio, Restaurante> join = root.join(Cardapio.Fields.restaurante);

            if (request.getIdRestaurante() != null){
                predicates.add(criteriaBuilder.equal(join.get(Restaurante.Fields.idRestaurante), request.getIdRestaurante()));
            }

            if (request.getNomeRestaurante()!= null){
                predicates.add(criteriaBuilder.equal(join.get(Restaurante.Fields.nome), request.getNomeRestaurante()));
            }
        }

        return predicates;
    }

    public void deletarPorId(Long id) {
        deleteById(id, Cardapio.class);
    }
}