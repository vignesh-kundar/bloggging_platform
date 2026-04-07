package com.vignesh.spring_blog.controller;

import com.vignesh.spring_blog.dto.HealthResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping("/ping")
    private ResponseEntity<HealthResponseDTO> health() {
        return new ResponseEntity<>(
                new HealthResponseDTO("Application is Healthy!" , HttpStatus.OK.value()) ,
                HttpStatus.OK
        );
    }

}