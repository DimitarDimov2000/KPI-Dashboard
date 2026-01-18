package com.project.kpi_dashboard.repository;

import com.project.kpi_dashboard.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByActiveTrueOrderByNameAsc();
    List<Drink> findByCategoryIgnoreCaseAndActiveTrueOrderByNameAsc(String category);
}
