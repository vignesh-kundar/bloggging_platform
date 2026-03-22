package com.vignesh.spring_blog.repository;

import com.vignesh.spring_blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag , Long> {

    Optional<Tag> findByName(String tagName);
}
