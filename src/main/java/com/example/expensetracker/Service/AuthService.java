package com.example.expensetracker.Service;

import com.example.expensetracker.DTO.Request.LoginRequest;
import com.example.expensetracker.DTO.Request.UserRequestDto;
import com.example.expensetracker.DTO.Response.EmailDetails;
import com.example.expensetracker.DTO.Response.LoginResponse;
import com.example.expensetracker.DTO.Response.UserResponseDto;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.AuthProvider;
import com.example.expensetracker.Repository.AuthRepository;
import com.example.expensetracker.Repository.UserDetailsRepository;
import com.example.expensetracker.Security.AuthUtill;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUtill authUtill;
    private final EmailService emailService;

    public UserResponseDto register( UserRequestDto userRequestDto) {
        User user=authRepository.findByEmail(userRequestDto.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeException("User already exists");
        }

        user=User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .authProvider(AuthProvider.LOCAL)
                .build();

        // email service
        EmailDetails welcomeEmail=new EmailDetails();
        welcomeEmail.setRecipient(user.getEmail());
        welcomeEmail.setSubject("Welcome to expense Tracker");
        welcomeEmail.setMsgBody("Hello " + user.getUsername() +
                ",\n\nWelcome! We are excited to have you on board. " +
                        "\n\nBest regards,\nThe Team");
        emailService.sendSimpleMail(welcomeEmail);

        authRepository.save(user);
        return new UserResponseDto(user.getId(),user.getEmail());
    }
    public LoginResponse login(@Valid LoginRequest loginRequest) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user= (User) authentication.getPrincipal();

        return new LoginResponse(user.getId(),authUtill.getAccessToken(user));
    }


}
