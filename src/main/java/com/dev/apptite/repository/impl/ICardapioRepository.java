package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardapioRepository extends JpaRepository<Cardapio, Long> {
}
