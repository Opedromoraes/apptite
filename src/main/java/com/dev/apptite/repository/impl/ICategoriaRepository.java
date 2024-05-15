package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.restaurante.idRestaurante = :idRestaurante")
    List<Categoria> findByIdRestaurante(@Param("idRestaurante") Long idRestaurante);
}
