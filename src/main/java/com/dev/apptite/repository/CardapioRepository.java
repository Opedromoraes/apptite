package com.dev.apptite.repository;

import com.dev.apptite.domain.entity.Cardapio;
import com.dev.apptite.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}
