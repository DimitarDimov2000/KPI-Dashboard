package com.project.kpi_dashboard.controller;

import com.project.kpi_dashboard.dto.CreateDrinkRequest;
import com.project.kpi_dashboard.dto.UpdateDrinkRequest;
import com.project.kpi_dashboard.model.Drink;
import com.project.kpi_dashboard.service.DrinkService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://vue-project-xpx3.onrender.com", "http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping({"/api/drinks", "/drinks"})
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<Drink> list(@RequestParam(required = false) String category) {
        return drinkService.listActive(category);
    }

    @PostMapping
    public Drink create(@Valid @RequestBody CreateDrinkRequest req) {
        return drinkService.create(req);
    }

    @PutMapping("/{id}")
    public Drink update(@PathVariable Long id, @Valid @RequestBody UpdateDrinkRequest req) {
        return drinkService.update(id, req);
    }

    @PatchMapping("/{id}/deactivate")
    public Drink deactivate(@PathVariable Long id) {
        return drinkService.deactivate(id);
    }
}
