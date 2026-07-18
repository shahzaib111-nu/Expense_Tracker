package com.example.expensetracker.Security;

import com.example.expensetracker.Enum.AuthProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.example.expensetracker.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthUtill {

    //key component
    @Value("${jwt.secretkey}")
    private String jwtSecret ;

    // header component
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String getAccessToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("name", user.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60*60)) // 1 day
                .signWith(getSecretKey())
                .compact();
    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public AuthProvider getProviderTypeFromRegistrationId(String registrationId) {
        return switch (registrationId) {
            case "google" -> AuthProvider.GOOGLE;
            case "github" -> AuthProvider.GITHUB;
            default -> throw new IllegalArgumentException("Unknown registration ID: " + registrationId);
        };

    }

    public String determineProviderIdFromAuthUser(OAuth2User oAuth2User, String registrationId) {
        String providerId=switch (registrationId) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();

            default -> {
                log.error("Unknown registration ID: {}", registrationId);
                throw new IllegalArgumentException("Unknown registration ID: " + registrationId);
            }
        };
        if (providerId == null || providerId.isBlank()) {
            log.error("Invalid provider ID for registration ID: {}", registrationId);
            throw new IllegalArgumentException("Invalid provider ID for registration ID: " + registrationId);
        }
        return providerId;

    }
}