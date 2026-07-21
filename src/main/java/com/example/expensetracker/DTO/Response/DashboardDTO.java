package com.example.expensetracker.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDTO {
    private double income;
    private double expense;
    private double balance;
    private int count;
}
