package com.project.kpi_dashboard;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> getAll() {
        return drinkRepository.findAll();
    }

    public Drink create(Drink drink) {
        return drinkRepository.save(drink);
    }
}
