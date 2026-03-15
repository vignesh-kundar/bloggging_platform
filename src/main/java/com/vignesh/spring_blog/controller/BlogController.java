package com.vignesh.spring_blog.controller;

import com.vignesh.spring_blog.DTO.BlogPostDTO;
import com.vignesh.spring_blog.DTO.BlogResponseDTO;
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

    @PostMapping("/posts")
    public ResponseEntity<Blog> addBlogPosts(@RequestBody @Validated BlogPostDTO blog) {
        Blog blogResponse = blogService.addBlog(blog);
        return new ResponseEntity<>(blogResponse , HttpStatus.CREATED);
    }


}
