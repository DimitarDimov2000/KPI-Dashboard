package com.project.kpi_dashboard.service;

import com.project.kpi_dashboard.dto.CreateDrinkRequest;
import com.project.kpi_dashboard.dto.UpdateDrinkRequest;
import com.project.kpi_dashboard.exception.NotFoundException;
import com.project.kpi_dashboard.model.Drink;
import com.project.kpi_dashboard.model.StockItem;
import com.project.kpi_dashboard.repository.DrinkRepository;
import com.project.kpi_dashboard.repository.StockItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final StockItemRepository stockItemRepository;

    public DrinkService(DrinkRepository drinkRepository, StockItemRepository stockItemRepository) {
        this.drinkRepository = drinkRepository;
        this.stockItemRepository = stockItemRepository;
    }

    public List<Drink> listActive(String category) {
        if (category == null || category.isBlank()) {
            return drinkRepository.findByActiveTrueOrderByNameAsc();
        }
        return drinkRepository.findByCategoryIgnoreCaseAndActiveTrueOrderByNameAsc(category);
    }

    @Transactional
    public Drink create(CreateDrinkRequest req) {
        Drink drink = new Drink(req.name, req.price, req.category);
        Drink saved = drinkRepository.save(drink);
        // ensure a stock row exists
        StockItem stockItem = new StockItem(saved, 0, 0);
        stockItemRepository.save(stockItem);
        return saved;
    }

    @Transactional
    public Drink update(Long id, UpdateDrinkRequest req) {
        Drink existing = drinkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Drink not found: " + id));
        existing.setName(req.name);
        existing.setPrice(req.price);
        existing.setCategory(req.category);
        existing.setActive(req.active);
        return drinkRepository.save(existing);
    }

    @Transactional
    public Drink deactivate(Long id) {
        Drink existing = drinkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Drink not found: " + id));
        existing.setActive(false);
        return drinkRepository.save(existing);
    }

    public Drink getById(Long id) {
        return drinkRepository.findById(id).orElseThrow(() -> new NotFoundException("Drink not found: " + id));
    }
}
