package com.dev.apptite.repository;

import com.dev.apptite.api.controller.categoria.request.CategoriaFilterRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.entity.Restaurante;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.dev.apptite.domain.enums.CriteriaTypeEnum.LIKE;

@Repository
public class CategoriaRepository extends CriteriaRepository<Categoria> {

    public Page<Categoria> findPaginated(Pageable pageable, CategoriaFilterRequest request) {
        return findPaginated(pageable, request, Categoria.class);
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Categoria> root, Object filterRequest) {

        CategoriaFilterRequest request = (CategoriaFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, "nome", request.getNome(), LIKE);

        return predicates;
    }
}