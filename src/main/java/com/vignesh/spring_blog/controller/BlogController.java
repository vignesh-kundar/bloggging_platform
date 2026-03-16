package com.vignesh.spring_blog.controller;

import com.vignesh.spring_blog.dto.BlogPostDTO;
import com.vignesh.spring_blog.dto.BlogResponseDTO;
import com.vignesh.spring_blog.entity.Blog;
import com.vignesh.spring_blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/posts")
    public ResponseEntity<List<BlogResponseDTO>> getAllBlogPosts() {
        return new ResponseEntity<>(blogService.findAllBlogs() , HttpStatus.OK);
    }

    @GetMapping("/posts/{post_id}")
    public ResponseEntity<BlogResponseDTO> getBlogPostById(@PathVariable Long post_id) {
        return new ResponseEntity<>(blogService.findBlogPostById(post_id) , HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Blog> addBlogPosts(@RequestBody @Validated BlogPostDTO blog) {
        Blog blogResponse = blogService.addBlog(blog);
        return new ResponseEntity<>(blogResponse , HttpStatus.CREATED);
    }


}
