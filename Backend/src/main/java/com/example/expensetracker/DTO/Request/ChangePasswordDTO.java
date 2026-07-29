package com.example.expensetracker.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTO {
    @NotBlank(message = "Previous password is required")
    private String previousPassword;
    @NotBlank(message = "New password is required")
    private String newPassword;
}
