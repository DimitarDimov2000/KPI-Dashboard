package com.project.kpi_dashboard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateDrinkRequest {
    @NotBlank
    public String name;

    @Min(0)
    public double price;

    @NotBlank
    public String category;
}
