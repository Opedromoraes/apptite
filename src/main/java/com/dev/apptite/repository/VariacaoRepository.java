package com.dev.apptite.repository;

import com.dev.apptite.api.controller.variacao.request.VariacaoFilterRequest;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.entity.Variacao;
import com.dev.apptite.domain.exceptions.DataBaseException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.repository.criteria.CriteriaRepository;
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

import static com.dev.apptite.domain.enums.CriteriaTypeEnum.LIKE;

@Repository
@AllArgsConstructor
public class VariacaoRepository extends CriteriaRepository<Variacao> {

    @Transactional(readOnly = true)
    public Page<Variacao> buscarPorFiltroComPaginacao(@NonNull final Pageable pageable,
                                                      @NonNull final VariacaoFilterRequest request) {
        return findPaginated(pageable, request, Variacao.class);
    }

    @Transactional(readOnly = true)
    public Optional<Variacao> buscarPorIdOptional(@NonNull final Long id) {
        return findById(id, Variacao.class);
    }

    @Transactional(readOnly = true)
    public Variacao buscarPorId(@NonNull Long id) {
        return findById(id, Variacao.class)
                .orElseThrow(() -> new NotFoundException("Variação não encontrada: " + id));
    }

    @Transactional
    public Variacao salvar(@NonNull Variacao variacao) {
        try {
            return save(variacao);
        } catch (Exception e) {
            throw new DataBaseException("Erro ao salvar variação", e);
        }
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Variacao> root, Object filterRequest) {

        VariacaoFilterRequest request = (VariacaoFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, Variacao.Fields.nome, request.getNome(), LIKE);

        if (request.getIdItem() != null) {
            Join<Variacao, Categoria> join = root.join(Variacao.Fields.item);

            predicates.add(criteriaBuilder.equal(join.get(Categoria.Fields.idCategoria), request.getIdItem()));
        }

        return predicates;
    }

    public void deletarPorId(Long id) {
        deleteById(id, Variacao.class);
    }
}