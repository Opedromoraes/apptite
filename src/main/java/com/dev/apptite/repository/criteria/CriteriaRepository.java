package com.dev.apptite.repository.criteria;

import com.dev.apptite.domain.enums.CriteriaTypeEnum;
import com.dev.apptite.domain.exceptions.DataBaseException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class CriteriaRepository<T> {

    protected EntityManager entityManager;

    public Optional<T> findById(Object id, Class<T> entityClass) {
        return Optional.of(entityManager.find(entityClass, id));
    }

    protected T save(T entity) {
        if (entityManager.contains(entity)) {
            return entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
            return entity;
        }
    }

    public void deleteById(Object id, Class<T> entityClass) {
        Optional<T> entity = findById(id, entityClass);
        if (entity.isPresent()) {
            entityManager.remove(entity.get());
        } else {
            throw new DataBaseException("Entity not found: " + id);
        }
    }

    public Page<T> findPaginated(Pageable pageable, Object filterRequest, Class<T> entityClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = predicateCriteriaQuery(criteriaBuilder, root, filterRequest);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        long totalRows = getTotalRows(criteriaBuilder, filterRequest, entityClass);

        List<T> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, totalRows);
    }

    protected abstract List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<T> root, Object filterRequest);

    private long getTotalRows(CriteriaBuilder criteriaBuilder, Object filterRequest, Class<T> entityClass) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> countRoot = countQuery.from(entityClass);

        List<Predicate> countPredicates = predicateCriteriaQuery(criteriaBuilder, countRoot, filterRequest);
        countQuery.select(criteriaBuilder.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    protected void addOptionalCriteria(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<T> root, String fieldName, Object criteriaField, CriteriaTypeEnum criteriaType) {
        if (Objects.nonNull(criteriaField)) {
            switch (criteriaType) {
                case IN:
                    predicates.add(root.get(fieldName).in(criteriaField));
                    break;
                case LIKE:
                    predicates.add(criteriaBuilder.like(root.get(fieldName), "%" + criteriaField + "%"));
                    break;
                default:
                    predicates.add(criteriaBuilder.equal(root.get(fieldName), criteriaField));
                    break;
            }
        }
    }
}