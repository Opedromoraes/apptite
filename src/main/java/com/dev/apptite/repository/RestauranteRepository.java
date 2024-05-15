package com.dev.apptite.repository;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.repository.impl.IRestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class RestauranteRepository {

    private final IRestauranteRepository repository;
    private final EntityManager entityManager;

    public Page<Restaurante> findPaginated(Pageable pageable, RestauranteFilterRequest request) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        predicateCriteriaQuery(criteriaQuery,root,request);
        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);


        return null;
    }
//        query.setFirstResult(pageable.getPage()-1 * pageable.getSize());

    private <T>List<Predicate> predicateCriteriaQuery(CriteriaQuery <T> criteriaQuery,Root <Restaurante> root, RestauranteFilterRequest request){
        List<Predicate> predicate = new ArrayList<>();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        if (Objects.nonNull(request.getNome())){
            predicate.add(root.get(Restaurante.Fields.nome).in(request.getNome()));
        }
        if (Objects.nonNull(request.getEndereco())){
            predicate.add(root.get(Restaurante.Fields.endereco).in(request.getEndereco()));
        }
        criteriaQuery.where(predicate.toArray(predicate.toArray(new Predicate[0])));
        return predicate;
    }


}
