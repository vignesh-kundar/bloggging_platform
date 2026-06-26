package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.entity.Users;
import com.vignesh.spring_blog.repository.UsersRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserCacheService {

    private UsersRepository usersRepository;

    UserCacheService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Cacheable("users")
    public Users findUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No Users with email Id found!"));
    }
}
