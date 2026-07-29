package com.example.expensetracker.DTO.Request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    String profilePic;
    @NotBlank(message = "Name is required")
    String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;
    @PositiveOrZero(message = "Budget must be a positive number")
    Double budget;
}