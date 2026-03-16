package com.vignesh.spring_blog.repository;

import com.vignesh.spring_blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog , Long> {

    @Query(value = "SELECT b FROM Blog b WHERE " +
            "b.category LIKE %:term% OR " +
            "b.content LIKE %:term% OR " +
            "b.title LIKE %:term%")
    List<Blog> findAllByTerm(@Param("term") String term);

}
