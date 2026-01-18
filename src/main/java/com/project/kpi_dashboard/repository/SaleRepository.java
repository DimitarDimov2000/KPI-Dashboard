package com.project.kpi_dashboard.repository;

import com.project.kpi_dashboard.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByTimestampBetweenOrderByTimestampDesc(Instant from, Instant to);
}
