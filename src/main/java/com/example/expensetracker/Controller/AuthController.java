package com.example.expensetracker.Controller;

import com.example.expensetracker.DTO.Request.LoginRequest;
import com.example.expensetracker.DTO.Request.UserRequestDto;
import com.example.expensetracker.DTO.Response.LoginResponse;
import com.example.expensetracker.DTO.Response.UserResponseDto;
import com.example.expensetracker.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.register(userRequestDto));
    }
    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        // Implement login logic here
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
