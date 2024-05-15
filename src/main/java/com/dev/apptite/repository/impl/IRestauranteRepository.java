package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestauranteRepository extends JpaRepository<Restaurante, Long> {
}
