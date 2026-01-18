package com.project.kpi_dashboard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateSaleRequest {
    @NotNull
    public Long drinkId;

    @Min(1)
    public int quantity;
}
