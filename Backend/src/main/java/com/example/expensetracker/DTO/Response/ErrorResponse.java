package com.example.expensetracker.DTO.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime now, String message, String description) {
        this.timestamp = now;
        this.message = message;
        this.details = description;
    }
}
