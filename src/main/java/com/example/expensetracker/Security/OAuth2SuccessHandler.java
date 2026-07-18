package com.example.expensetracker.Security;

import com.example.expensetracker.DTO.Response.LoginResponse;
import com.example.expensetracker.Service.AuthService;
import com.example.expensetracker.Service.OAuth2Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final OAuth2Service oAuth2Service;
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String registrationId=oauth2Token.getAuthorizedClientRegistrationId();

        log.info("Success Handler Called");
        ResponseEntity<LoginResponse> loginResponse = oAuth2Service.handleOAuth2Login(oAuth2User, registrationId);
        response.setStatus(loginResponse.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        LoginResponse loginResponseBody =
                oAuth2Service.handleOAuth2Login(oAuth2User, registrationId).getBody();
        String jwt =loginResponseBody.getJwt();
        response.sendRedirect( "http://127.0.0.1:5500/dashboard.html?token=" + URLEncoder.encode(jwt, StandardCharsets.UTF_8));

    }
}
