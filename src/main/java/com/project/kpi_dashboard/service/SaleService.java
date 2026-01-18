package com.project.kpi_dashboard.service;

import com.project.kpi_dashboard.dto.CreateSaleRequest;
import com.project.kpi_dashboard.exception.BadRequestException;
import com.project.kpi_dashboard.exception.NotFoundException;
import com.project.kpi_dashboard.model.Drink;
import com.project.kpi_dashboard.model.Sale;
import com.project.kpi_dashboard.model.StockItem;
import com.project.kpi_dashboard.repository.SaleRepository;
import com.project.kpi_dashboard.repository.StockItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final StockItemRepository stockItemRepository;
    private final DrinkService drinkService;

    public SaleService(SaleRepository saleRepository, StockItemRepository stockItemRepository, DrinkService drinkService) {
        this.saleRepository = saleRepository;
        this.stockItemRepository = stockItemRepository;
        this.drinkService = drinkService;
    }

    public List<Sale> listBetween(Instant from, Instant to) {
        return saleRepository.findByTimestampBetweenOrderByTimestampDesc(from, to);
    }

    @Transactional
    public Sale create(CreateSaleRequest req) {
        Drink drink = drinkService.getById(req.drinkId);

        StockItem stock = stockItemRepository.findByDrinkId(req.drinkId)
                .orElseThrow(() -> new NotFoundException("Stock item not found for drink: " + req.drinkId));

        if (stock.getStockLevel() < req.quantity) {
            throw new BadRequestException("Not enough stock. Current stock: " + stock.getStockLevel());
        }

        stock.setStockLevel(stock.getStockLevel() - req.quantity);
        stockItemRepository.save(stock);

        Sale sale = new Sale(drink, req.quantity, drink.getPrice());
        return saleRepository.save(sale);
    }
}
