package com.example.expensetracker.Service;

import com.example.expensetracker.DTO.Request.ChangePasswordDTO;
import com.example.expensetracker.DTO.Response.ProfileDTO;
import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsRepository userDetailsRepository;
    public Void changePassword(User user, ChangePasswordDTO request) {

        // check previous password

        if (!passwordEncoder.matches(request.getPreviousPassword(), user.getPassword())) {
            throw new RuntimeException("Previous password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userDetailsRepository.save(user);
        return null;
    }

    public ProfileDTO getProfile(User user) {
        User user1=userDetailsRepository.findById(user.getId()).orElse(null);
        return new ProfileDTO(user1.getProfilePic(),user1.getName(),user1.getEmail(),user1.getBudget());
    }
}
