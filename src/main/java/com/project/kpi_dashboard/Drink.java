package com.project.kpi_dashboard;

public class Drink {
    private Long id;
    private String name;
    private double price;

    public Drink(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}