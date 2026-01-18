package com.project.kpi_dashboard.dto;

import jakarta.validation.constraints.Min;

public class RestockRequest {
    @Min(1)
    public int amount;
}
