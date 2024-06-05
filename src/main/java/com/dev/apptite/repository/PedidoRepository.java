package com.dev.apptite.repository;

import com.dev.apptite.api.controller.pedido.request.PedidoFilterRequest;
import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.exceptions.DataBaseException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.repository.criteria.CriteriaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dev.apptite.domain.enums.CriteriaTypeEnum.EQUAL;

@Repository
public class PedidoRepository extends CriteriaRepository<Pedido> {

    @Transactional(readOnly = true)
    public Page<Pedido> buscarPorFiltroComPaginacao(@NonNull final Pageable pageable,
                                                    @NonNull final PedidoFilterRequest request) {
        return findPaginated(pageable, request, Pedido.class);
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorIdOptional(@NonNull final Long id) {
        return findById(id, Pedido.class);
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(@NonNull final Long id) {
        return findById(id, Pedido.class)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado: " + id));
    }

    @Transactional
    public Pedido salvar(@NonNull final Pedido pedido) {
        try {
            return save(pedido);
        } catch (Exception e) {
            throw new DataBaseException("Erro ao salvar pedido", e);
        }
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(@NonNull final CriteriaBuilder criteriaBuilder,
                                                     @NonNull final Root<Pedido> root,
                                                     @NonNull final Object filterRequest) {
        PedidoFilterRequest request = (PedidoFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();
        addOptionalCriteria(criteriaBuilder, predicates, root, Pedido.Fields.status, request.getNome(), EQUAL);
        return predicates;
    }
}