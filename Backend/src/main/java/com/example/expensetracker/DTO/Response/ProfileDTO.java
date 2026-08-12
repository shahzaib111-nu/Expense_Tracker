package com.example.expensetracker.DTO.Response;

import com.example.expensetracker.Enum.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    private String profilePic;
    private String name;
    private String email;
    private double budget;
    private AuthProvider authProvider;
}
