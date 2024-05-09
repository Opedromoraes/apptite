package com.dev.apptite.repository;

import com.dev.apptite.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findCategoriasId(List<Long> categoriasId);

}
