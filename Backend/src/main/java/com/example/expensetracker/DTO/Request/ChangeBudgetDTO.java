package com.example.expensetracker.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ChangeBudgetDTO {

    @NotNull
    @PositiveOrZero
    private Double budget;
}
