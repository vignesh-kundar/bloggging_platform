package com.vignesh.spring_blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping("/ping")
    String health() {
        return "Pong!!. Application is health.";
    }

}