package com.vignesh.spring_blog.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping("/ping")
    private ResponseEntity<String> health() {
        return new ResponseEntity<>("Pong!!. Application is health." , HttpStatus.OK);
    }



}