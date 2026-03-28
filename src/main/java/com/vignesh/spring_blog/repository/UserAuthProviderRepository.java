package com.vignesh.spring_blog.repository;

import com.vignesh.spring_blog.entity.UserAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthProviderRepository extends JpaRepository<UserAuthProvider , Long> {
    Optional<UserAuthProvider> findByProviderAndProviderId(String Provider , String providerId);
}
