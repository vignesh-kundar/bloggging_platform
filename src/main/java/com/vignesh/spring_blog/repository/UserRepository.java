package com.vignesh.spring_blog.repository;

import com.vignesh.spring_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
