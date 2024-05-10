package com.dev.apptite.repository;

import com.dev.apptite.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.restaurante.idRestaurante = :idRestaurante")
    List<Categoria> findCategoriasByIdRestaurante(@Param("idRestaurante") Long idRestaurante);

    @Query("SELECT c FROM Categoria c WHERE c.nome = :nome")
    Optional<Categoria> findByName(@Param("nome") String nome);
}
