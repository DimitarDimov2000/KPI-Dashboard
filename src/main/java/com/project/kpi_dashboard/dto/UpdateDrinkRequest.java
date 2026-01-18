package com.project.kpi_dashboard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UpdateDrinkRequest {
    @NotBlank
    public String name;

    @Min(0)
    public double price;

    @NotBlank
    public String category;

    public boolean active = true;
}
