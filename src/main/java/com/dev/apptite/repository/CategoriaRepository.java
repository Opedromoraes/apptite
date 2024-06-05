package com.dev.apptite.repository;

import com.dev.apptite.api.controller.categoria.request.CategoriaFilterRequest;

import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.exceptions.DataBaseException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.repository.criteria.CriteriaRepository;
import com.dev.apptite.repository.impl.ICategoriaRepository;
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
public class CategoriaRepository extends CriteriaRepository<Categoria> {

    private final ICategoriaRepository repository;

    @Transactional(readOnly = true)
    public Page<Categoria> buscarPorFiltroComPaginacao(@NonNull final Pageable pageable,
                                                       @NonNull final CategoriaFilterRequest request) {
        return findPaginated(pageable, request, Categoria.class);
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> buscarPorIdOptional(@NonNull final Long id) {
        return findById(id, Categoria.class);
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(@NonNull Long id) {
        return findById(id, Categoria.class)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrado: " + id));
    }

    @Transactional
    public Categoria salvar(@NonNull Categoria categoria) {
        try {
            return save(categoria);
        } catch (Exception e) {
            throw new DataBaseException("Erro ao salvar categoria", e);
        }
    }

    @Override
    protected List<Predicate> predicateCriteriaQuery(CriteriaBuilder criteriaBuilder, Root<Categoria> root, Object filterRequest) {

        CategoriaFilterRequest request = (CategoriaFilterRequest) filterRequest;
        List<Predicate> predicates = new ArrayList<>();

        addOptionalCriteria(criteriaBuilder, predicates, root, Categoria.Fields.nome, request.getNome(), LIKE);

        if (request.getIdCardapio() != null ) {
            Join<Categoria, Cardapio> join = root.join(Categoria.Fields.cardapio);

            predicates.add(criteriaBuilder.equal(join.get(Cardapio.Fields.idCardapio), request.getIdCardapio()));
        }

        return predicates;
    }

    public boolean existsById(Long id) {
        return findById(id, Categoria.class).isPresent();
    }

    public List<Categoria> buscarPorIdRestaurante(Long idRestaurante) {
        List<Categoria> restaurantes = repository.findByIdRestaurante(idRestaurante);

        if (restaurantes.isEmpty()) {
            throw new NotFoundException("Nenhuma categoria encontrada para o restaurante: {} ", idRestaurante);
        }

        return restaurantes;
    }

    public void deletarPorId(Long id) {
        deleteById(id, Categoria.class);
    }
}