package com.example.expensetracker.DTO.Response;

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
}
