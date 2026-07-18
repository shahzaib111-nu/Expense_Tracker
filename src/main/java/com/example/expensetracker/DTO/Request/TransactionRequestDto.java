package com.example.expensetracker.DTO.Request;

import com.example.expensetracker.Enum.Category;
import com.example.expensetracker.Enum.Type;
import lombok.Data;
import lombok.Value;
import java.time.LocalDate;

@Data
public class TransactionRequestDto {
    String title;
    Double amount;
    Type type;
    Category category;
    LocalDate date;
}