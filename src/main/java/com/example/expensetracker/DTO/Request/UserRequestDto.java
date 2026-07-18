package com.example.expensetracker.DTO.Request;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @Nonnull
    String name;
    @Nonnull
    String email;
    @Nonnull
    String password;
}