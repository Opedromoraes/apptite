package com.dev.apptite.repository;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.entity.Restaurante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class RestauranteRepository {

    private final EntityManager entityManager;

    public Page<Restaurante> findPaginated(Pageable pageable, RestauranteFilterRequest request) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        List<Predicate> predicates = predicateCriteriaQuery(criteriaBuilder, root, request);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        long totalRows = getTotalRows(criteriaBuilder, root, predicates);

        List<Restaurante> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, totalRows);
    }

    private List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Restaurante> root, RestauranteFilterRequest request) {
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteriaIs(criteriaBuilder, predicates, root, Restaurante.Fields.nome, request.getNome());
        addOptionalCriteriaIn(predicates, root, Restaurante.Fields.endereco, request.getEndereco());

        return predicates;
    }

    private long getTotalRows(CriteriaBuilder criteriaBuilder, Root<Restaurante> root, List<Predicate> predicates) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(root));
        countQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private void addOptionalCriteriaIs(CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
                                       Root<Restaurante> root, String fieldName, Object criteriaField) {
        if (Objects.nonNull(criteriaField)) {
            predicates.add(criteriaBuilder.equal(root.get(fieldName), criteriaField));
        }
    }

    private void addOptionalCriteriaIn(List<Predicate> predicates, Root<Restaurante> root, String fieldName,
                                       Object criteriaField) {
        if (Objects.nonNull(criteriaField)) {
            predicates.add(root.get(fieldName).in(criteriaField));
        }
    }
}
