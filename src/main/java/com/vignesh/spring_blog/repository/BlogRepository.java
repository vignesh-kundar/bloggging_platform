package com.vignesh.spring_blog.repository;

import com.vignesh.spring_blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog , Long> {
}
