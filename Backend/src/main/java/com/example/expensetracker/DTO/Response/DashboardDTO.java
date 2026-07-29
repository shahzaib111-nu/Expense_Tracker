package com.example.expensetracker.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardDTO {
    private double expense;
    private double dailyAvg;
    private double budget;
    private double remain;
    private int count;
    private List<CategoryExpensedto>expensebyCategory;
}
