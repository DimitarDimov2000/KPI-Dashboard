package com.project.kpi_dashboard;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "https://vue-project-xpx3.onrender.com")
@RestController
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<Drink> getAll() {
        return drinkService.getAll();
    }

    @PostMapping
    public Drink create(@RequestBody Drink drink) {
        return drinkService.create(drink);
    }
}