package com.vignesh.spring_blog.repository;

import com.vignesh.spring_blog.entity.Users;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users , Long> {
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(@NotEmpty String email);

    boolean existsByUserName(@NotEmpty String userName);
}
