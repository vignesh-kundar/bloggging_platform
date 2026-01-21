package com.vignesh.spring_blog.controller;

import com.vignesh.spring_blog.entity.User;
import com.vignesh.spring_blog.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @GetMapping("/user")
    public String checkUserApiStatus() {
        return "OK";
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@Validated @RequestBody User user) {
        return userServiceImplementation.add(user);
    }
}
