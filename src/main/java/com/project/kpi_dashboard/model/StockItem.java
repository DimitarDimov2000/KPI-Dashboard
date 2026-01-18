package com.project.kpi_dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "stock_items", uniqueConstraints = @UniqueConstraint(columnNames = "drink_id"))
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Min(0)
    @Column(nullable = false)
    private int stockLevel = 0;

    @Min(0)
    @Column(nullable = false)
    private int reorderThreshold = 0;

    public StockItem() {
        // JPA
    }

    public StockItem(Drink drink, int stockLevel, int reorderThreshold) {
        this.drink = drink;
        this.stockLevel = stockLevel;
        this.reorderThreshold = reorderThreshold;
    }

    public Long getId() {
        return id;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }
}

