package com.example.expensetracker.Security;

import com.example.expensetracker.DTO.Response.LoginResponse;
import com.example.expensetracker.Exception.UserAlreadyPresentException;
import com.example.expensetracker.Service.OAuth2Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final String FRONTEND_BASE_URL = "http://127.0.0.1:5500";
    private final OAuth2Service oAuth2Service;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String registrationId = oauth2Token.getAuthorizedClientRegistrationId();
        log.info("OAuth2 Success Handler Called for provider: {}", registrationId);

        try {
            // 1. Call service ONCE
            ResponseEntity<LoginResponse> responseEntity = oAuth2Service.handleOAuth2Login(oAuth2User, registrationId);
            LoginResponse loginResponseBody = responseEntity.getBody();

            if (loginResponseBody != null && loginResponseBody.getJwt() != null) {
                String jwt = loginResponseBody.getJwt();

                // 2. Redirect to Frontend Dashboard with JWT token
                String redirectUrl = FRONTEND_BASE_URL + "/dashboard.html?token=" + URLEncoder.encode(jwt, StandardCharsets.UTF_8);
                response.sendRedirect(redirectUrl);
                return;
            }

            redirectToLoginWithError(response, "OAuth2 login did not produce a token.");

        } catch (UserAlreadyPresentException ex) {
            log.warn("UserAlreadyPresentException: {}", ex.getMessage());
            redirectToLoginWithError(response, ex.getMessage());
        } catch (Exception ex) {
            log.error("Exception during OAuth2 login: {}", ex.getMessage(), ex);
            redirectToLoginWithError(response, "OAuth2 sign-in could not be completed. Please try again.");
        }
    }

    private void redirectToLoginWithError(HttpServletResponse response, String message) throws IOException {
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        response.sendRedirect(FRONTEND_BASE_URL + "/login.html?error=" + encodedMessage);
    }
}
