package com.project.kpi_dashboard.repository;

import com.project.kpi_dashboard.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
    Optional<StockItem> findByDrinkId(Long drinkId);
}

