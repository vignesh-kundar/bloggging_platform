package com.vignesh.spring_blog.security;

import com.vignesh.spring_blog.entity.Users;
import com.vignesh.spring_blog.repository.UsersRepository;
import com.vignesh.spring_blog.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.NoSuchElementException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (null==authHeader || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request ,response);
            return;
        }

        String authToken = authHeader.substring(7);
        if (!jwtService.isTokenValid(authToken)) {
            filterChain.doFilter(request , response);
            return;
        }

        String email = jwtService.extractEmail(authToken);
        Users user = usersRepository.findByEmail(email).orElseThrow( () -> new NoSuchElementException("No Usesr with email Id found!"));

    }

}
