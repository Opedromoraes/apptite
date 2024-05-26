package com.dev.apptite.repository;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
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
public class RestauranteRepository extends CriteriaRepository<Restaurante> {

    public Page<Restaurante> findPaginated(Pageable pageable, RestauranteFilterRequest request) {
        return findPaginated(pageable, request, Restaurante.class);
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Restaurante> root, Object filterRequest) {

        RestauranteFilterRequest request = (RestauranteFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, "nome", request.getNome(), LIKE);
        addOptionalCriteria(criteriaBuilder, predicates, root, "endereco", request.getEndereco(), LIKE);

        return predicates;
    }
}