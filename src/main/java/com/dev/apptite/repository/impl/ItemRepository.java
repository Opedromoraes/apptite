package com.dev.apptite.repository.impl;

import com.dev.apptite.api.controller.item.request.ItemFilterRequest;
import com.dev.apptite.domain.entity.Item;
import com.dev.apptite.domain.exceptions.DataBaseException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.repository.criteria.CriteriaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class ItemRepository extends CriteriaRepository<Item> {

    @Transactional(readOnly = true)
    public Page<Item> buscarPorFiltroComPaginacao(@NonNull final Pageable pageable,
                                                      @NonNull final ItemFilterRequest request) {
        return findPaginated(pageable, request, Item.class);
    }

    @Transactional(readOnly = true)
    public Optional<Item> buscarPorIdOptional(@NonNull final Long id) {
        return findById(id, Item.class);
    }

    @Transactional(readOnly = true)
    public Item buscarPorId(@NonNull Long id) {
        return findById(id, Item.class)
                .orElseThrow(() -> new NotFoundException("Item não encontrado: " + id));
    }

    @Transactional
    public Item salvar(@NonNull Item cardapio) {
        try {
            return save(cardapio);
        } catch (Exception e) {
            throw new DataBaseException("Erro ao salvar cardapio", e);
        }
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Item> root, Object filterRequest) {

        ItemFilterRequest request = (ItemFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, Item.Fields.nome, request.getNome(), LIKE);

        return predicates;
    }

    public void deletarPorId(Long id) {
        deleteById(id, Item.class);
    }
}