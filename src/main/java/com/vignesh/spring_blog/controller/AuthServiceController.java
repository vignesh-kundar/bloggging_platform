package com.vignesh.spring_blog.controller;

import com.vignesh.spring_blog.dto.AuthResponseToken;
import com.vignesh.spring_blog.dto.RegisterUserRequestDTO;
import com.vignesh.spring_blog.dto.UserLoginRequestDTO;
import com.vignesh.spring_blog.dto.UserResponseDTO;
import com.vignesh.spring_blog.entity.Users;
import com.vignesh.spring_blog.service.AuthService;
import com.vignesh.spring_blog.util.ResponseFormatter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthServiceController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseToken> registerUser(@Valid @RequestBody RegisterUserRequestDTO newUser) {
        String token = authService.registerUser(newUser.name(),  newUser.email(), newUser.userName(),newUser.password());
        AuthResponseToken response = new AuthResponseToken(token , "Successfully registered user");
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseToken> loginUser(@Valid @RequestBody UserLoginRequestDTO userLoginRequest) throws Exception {
            String token = authService.loginUser(userLoginRequest.email(), userLoginRequest.password());
            return new ResponseEntity<>(new AuthResponseToken(token , "Successfully logged-in!") , HttpStatus.OK);
    }

    @GetMapping("/userprofile")
    public ResponseEntity<UserResponseDTO> getUserProfile(@RequestHeader("Authorization") String jwtToken) {
        UserResponseDTO response = authService.getUserProfileFromContext(jwtToken);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
