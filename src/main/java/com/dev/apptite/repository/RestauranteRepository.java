package com.dev.apptite.repository;

import com.dev.apptite.api.controller.restaurante.request.RestauranteFilterRequest;
import com.dev.apptite.domain.entity.Restaurante;
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

import static com.dev.apptite.domain.enums.CriteriaTypeEnum.LIKE;

@Repository
public class RestauranteRepository extends CriteriaRepository<Restaurante> {

    @Transactional(readOnly = true)
    public Page<Restaurante> buscarPorFiltroComPaginacao(@NonNull final Pageable pageable,
                                                         @NonNull final RestauranteFilterRequest request) {
        return findPaginated(pageable, request, Restaurante.class);
    }

    @Transactional(readOnly = true)
    public Optional<Restaurante> buscarPorIdOptional(@NonNull final Long id) {
        return findById(id, Restaurante.class);
    }

    @Transactional(readOnly = true)
    public Restaurante buscarPorId(@NonNull final Long id) {
        return findById(id, Restaurante.class)
                .orElseThrow(() -> new NotFoundException("Restaurante não encontrado: " + id));
    }

    @Transactional
    public Restaurante salvar(@NonNull final Restaurante restaurante) {
        return save(restaurante);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            findById(id, Restaurante.class);
        } catch (NotFoundException e) {
            throw new NotFoundException("Restaurante não encontrado: " + id);
        }
    }

    public List<Restaurante> findByQueryString(String queryString){
        return findByQueryString(queryString, Restaurante.class);
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(@NonNull final CriteriaBuilder criteriaBuilder,
                                                     @NonNull final Root<Restaurante> root,
                                                     @NonNull final Object filterRequest) {
        RestauranteFilterRequest request = (RestauranteFilterRequest) filterRequest;

        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, Restaurante.Fields.nome, request.getNome(), LIKE);
        addOptionalCriteria(criteriaBuilder, predicates, root, Restaurante.Fields.endereco, request.getEndereco(), LIKE);

        return predicates;
    }
}