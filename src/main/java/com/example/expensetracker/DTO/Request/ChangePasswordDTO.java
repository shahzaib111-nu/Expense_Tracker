package com.example.expensetracker.DTO.Request;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String previousPassword;
    private String newPassword;
}
