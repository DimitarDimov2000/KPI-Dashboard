package com.project.kpi_dashboard.controller;

import com.project.kpi_dashboard.dto.CreateSaleRequest;
import com.project.kpi_dashboard.model.Sale;
import com.project.kpi_dashboard.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@CrossOrigin(origins = {"https://vue-project-xpx3.onrender.com", "http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> list(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        Instant now = Instant.now();
        Instant fromInstant = (from == null || from.isBlank()) ? now.minus(Duration.ofDays(7)) : Instant.parse(from);
        Instant toInstant = (to == null || to.isBlank()) ? now : Instant.parse(to);
        return saleService.listBetween(fromInstant, toInstant);
    }

    @PostMapping
    public Sale create(@Valid @RequestBody CreateSaleRequest req) {
        return saleService.create(req);
    }
}
