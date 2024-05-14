package com.dev.apptite.repository;

import com.dev.apptite.domain.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
    Optional<Cardapio> findByNome(String nome);
}
