package com.example.expensetracker.DTO.Response;

import com.example.expensetracker.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExpensedto {

    private Category category;
    private Double expense;
}
