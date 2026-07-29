package com.example.expensetracker.Security;

import com.example.expensetracker.Entity.User;
import com.example.expensetracker.Repository.UserDetailsRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final AuthUtill authUtill;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            log.info("Processing JWT authentication for request: {}", request.getRequestURI());
            String header=request.getHeader("Authorization");
            if (header==null || !header.startsWith("Bearer ")){
                log.warn("Missing or invalid Authorization header");
                filterChain.doFilter(request, response);
                return;
            }
            String token=header.substring(7);
            log.info("Extracted JWT token: {}", token);
            String username=authUtill.getEmailFromToken(token);
            log.info("Extracted username from JWT token: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userDetailsRepository.findByEmail(username).orElse(null);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, null
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
         filterChain.doFilter(request, response);
    }
}
