package com.example.expensetracker.DTO.Request;

import com.example.expensetracker.Enum.Category;
import com.example.expensetracker.Enum.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Value;
import java.time.LocalDate;

@Data
public class TransactionRequestDto {
    @NotBlank(message = "Title is required")
    String title;
    @NotBlank(message = "Amount is required")
    @PositiveOrZero(message = "Amount must be a positive number")
    Double amount;
    @NotBlank(message = "Category is required")
    Category category;
    @NotBlank(message = "Date is required")
    LocalDate date;
}