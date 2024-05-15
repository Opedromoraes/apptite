package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
