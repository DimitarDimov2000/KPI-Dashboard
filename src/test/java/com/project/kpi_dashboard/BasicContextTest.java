package com.project.kpi_dashboard;

import com.project.kpi_dashboard.repository.DrinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BasicContextTest {

    @Autowired
    DrinkRepository drinkRepository;

    @Test
    void contextLoads() {
        // if this test runs, Spring booted successfully
        assertThat(true).isTrue();
    }

    @Test
    void drinkRepositoryBeanExists() {
        assertThat(drinkRepository).isNotNull();
    }
}
