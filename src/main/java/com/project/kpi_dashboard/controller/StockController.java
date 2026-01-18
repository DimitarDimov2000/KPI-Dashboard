package com.project.kpi_dashboard.controller;

import com.project.kpi_dashboard.dto.RestockRequest;
import com.project.kpi_dashboard.dto.UpsertStockRequest;
import com.project.kpi_dashboard.model.StockItem;
import com.project.kpi_dashboard.service.StockService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://vue-project-xpx3.onrender.com", "http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockItem> listAll() {
        return stockService.listAll();
    }

    @PutMapping
    public StockItem upsert(@Valid @RequestBody UpsertStockRequest req) {
        return stockService.upsert(req);
    }

    @PostMapping("/{drinkId}/restock")
    public StockItem restock(@PathVariable Long drinkId, @Valid @RequestBody RestockRequest req) {
        return stockService.restock(drinkId, req);
    }
}
