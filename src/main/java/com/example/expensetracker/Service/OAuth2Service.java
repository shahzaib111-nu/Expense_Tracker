package com.example.expensetracker.Service;


import com.example.expensetracker.DTO.Response.LoginResponse;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Enum.AuthProvider;
import com.example.expensetracker.Repository.UserDetailsRepository;
import com.example.expensetracker.Security.AuthUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2Service {

        private final AuthUtill authUtill;
        private final UserDetailsRepository userDetailsRepository;


    public ResponseEntity<LoginResponse> handleOAuth2Login(OAuth2User oAuth2User, String registrationId) {
        if (oAuth2User == null) {
            throw new RuntimeException("OAuth2 authentication failed.");
        }

        AuthProvider providerType =
                authUtill.getProviderTypeFromRegistrationId(registrationId);

        String providerId =
                authUtill.determineProviderIdFromAuthUser(oAuth2User, registrationId);

        String email = oAuth2User.getAttribute("email");

        User user=userDetailsRepository.findByProviderIdAndAuthProvider(providerId, providerType).orElse(null);
        // login logic
        if(user!=null){
            log.info("Already has account direct login");
            return ResponseEntity.ok(new LoginResponse(user.getId(), authUtill.getAccessToken(user)));
        }
        // existing user
        User existingUser=userDetailsRepository.findByEmail(email).orElse(null);
        if(existingUser!=null){
            // check provider type
            if(existingUser.getAuthProvider() == providerType) {
                log.info("Existing user found with matching provider type");
                existingUser.setProviderId(providerId);
                userDetailsRepository.save(existingUser);
                return ResponseEntity.ok(new LoginResponse(existingUser.getId(), authUtill.getAccessToken(existingUser)));
            } else {
                throw new RuntimeException("User already exists with a different authentication provider.");
            }
        }

        User newUser=User.builder()
                .name(oAuth2User.getAttribute("name"))
                .email(email)
                .providerId(providerId)
                .password(null)
                .authProvider(providerType)
                .build();

        userDetailsRepository.save(newUser);
        return ResponseEntity.ok(new LoginResponse(newUser.getId(), authUtill.getAccessToken(newUser)));

    }
}
