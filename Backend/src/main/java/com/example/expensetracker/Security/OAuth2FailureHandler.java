package com.example.expensetracker.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class OAuth2FailureHandler implements AuthenticationFailureHandler {

    private final String frontendBaseUrl;

    public OAuth2FailureHandler(@Value("${app.frontend.base-url:http://127.0.0.1:5500}") String frontendBaseUrl) {
        this.frontendBaseUrl = frontendBaseUrl;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        String message = exception.getMessage() == null
                ? "OAuth2 authentication failed. Please try again."
                : exception.getMessage();

        log.error("OAuth2 login failed: {}", message, exception);

        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        response.sendRedirect(frontendBaseUrl + "/login.html?error=" + encodedMessage);
    }
}
