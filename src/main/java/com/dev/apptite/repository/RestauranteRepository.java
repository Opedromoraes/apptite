package com.dev.apptite.repository;

import com.dev.apptite.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
