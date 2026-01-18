package com.project.kpi_dashboard.service;

import com.project.kpi_dashboard.dto.RestockRequest;
import com.project.kpi_dashboard.dto.UpsertStockRequest;
import com.project.kpi_dashboard.exception.NotFoundException;
import com.project.kpi_dashboard.model.Drink;
import com.project.kpi_dashboard.model.StockItem;
import com.project.kpi_dashboard.repository.StockItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {

    private final StockItemRepository stockItemRepository;
    private final DrinkService drinkService;

    public StockService(StockItemRepository stockItemRepository, DrinkService drinkService) {
        this.stockItemRepository = stockItemRepository;
        this.drinkService = drinkService;
    }

    public List<StockItem> listAll() {
        return stockItemRepository.findAll();
    }

    @Transactional
    public StockItem upsert(UpsertStockRequest req) {
        Drink drink = drinkService.getById(req.drinkId);
        StockItem stock = stockItemRepository.findByDrinkId(req.drinkId)
                .orElseGet(() -> new StockItem(drink, 0, 0));
        stock.setDrink(drink);
        stock.setStockLevel(req.stockLevel);
        stock.setReorderThreshold(req.reorderThreshold);
        return stockItemRepository.save(stock);
    }

    @Transactional
    public StockItem restock(Long drinkId, RestockRequest req) {
        StockItem stock = stockItemRepository.findByDrinkId(drinkId)
                .orElseThrow(() -> new NotFoundException("Stock item not found for drink: " + drinkId));
        stock.setStockLevel(stock.getStockLevel() + req.amount);
        return stockItemRepository.save(stock);
    }
}
