package com.example.expensetracker.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeUsernameDTO {

    @NotBlank
    private String name;
}
