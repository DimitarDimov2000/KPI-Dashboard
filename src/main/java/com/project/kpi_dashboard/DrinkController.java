package com.project.kpi_dashboard;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "https://vue-project-xpx3.onrender.com")
@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @GetMapping
    public List<Drink> getAllDrinks() {
        return List.of(
                new Drink(1L, "Beer", 5.0),
                new Drink(2L, "Martini", 8.0),
                new Drink(3L, "Aperol Spritz", 7.5)
        );
    }
}