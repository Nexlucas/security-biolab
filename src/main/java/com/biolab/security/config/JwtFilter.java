package com.biolab.security.config;

import com.biolab.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.catalina.UserDatabase;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter {

    private final JwtService jwtService;
    private final UserDetailsService detailsService;

    public JwtFilter(JwtService jwtService, UserDetailsService detailsService) {
        this.jwtService = jwtService;
        this.detailsService = detailsService;
    }


    protected void doFilterInternal(HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain) throws ServletException, IOException {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer" )) {
            String token = header.substring(7);
            String cpf = jwtService.getCpf(token);
            UserDetails user = detailsService.loadUserByUsername(cpf);
            var auth = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(req, res);
    }
}
