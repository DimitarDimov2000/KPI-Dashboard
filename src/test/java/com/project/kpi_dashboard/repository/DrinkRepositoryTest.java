package com.project.kpi_dashboard.repository;

import com.project.kpi_dashboard.model.Drink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DrinkRepositoryTest {

    @Autowired
    DrinkRepository drinkRepository;

    @Test
    void save_assignsId() {
        Drink saved = drinkRepository.save(new Drink("Beer", 5.0, "Beer"));
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void findById_returnsSavedDrink() {
        Drink saved = drinkRepository.save(new Drink("Aperol", 8.0, "Cocktail"));
        assertThat(drinkRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void findAll_returnsAllRows() {
        drinkRepository.save(new Drink("Beer", 5.0, "Beer"));
        drinkRepository.save(new Drink("Martini", 9.0, "Cocktail"));

        List<Drink> all = drinkRepository.findAll();
        assertThat(all.size()).isGreaterThanOrEqualTo(2);
    }
}
