package com.example.expensetracker.Service;

import com.example.expensetracker.Repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userDetailsRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

    }
}
