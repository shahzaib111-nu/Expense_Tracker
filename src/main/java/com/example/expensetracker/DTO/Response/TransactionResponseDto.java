package com.example.expensetracker.DTO.Response;

import com.example.expensetracker.Enum.Category;
import com.example.expensetracker.Enum.Type;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    Long id;
    String title;
    Double amount;
    Type type;
    Category category;
    LocalDate date;


}