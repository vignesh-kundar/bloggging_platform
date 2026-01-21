package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.entity.User;
import com.vignesh.spring_blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> add(User user) {
        userRepository.save(user);
        return new ResponseEntity("Success" , HttpStatus.CREATED);
    }
}
