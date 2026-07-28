package com.example.expensetracker.Exception;

public class UserAlreadyPresentException extends RuntimeException {
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
