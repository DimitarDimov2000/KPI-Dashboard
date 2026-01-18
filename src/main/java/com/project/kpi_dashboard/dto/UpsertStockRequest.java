package com.project.kpi_dashboard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpsertStockRequest {
    @NotNull
    public Long drinkId;

    @Min(0)
    public int stockLevel;

    @Min(0)
    public int reorderThreshold;
}
