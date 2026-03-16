package com.vignesh.spring_blog.controller;

import com.vignesh.spring_blog.dto.BlogPostDTO;
import com.vignesh.spring_blog.dto.BlogResponseDTO;
import com.vignesh.spring_blog.entity.Blog;
import com.vignesh.spring_blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/posts")
    public ResponseEntity<List<BlogResponseDTO>> getAllBlogPosts(@RequestParam(required = false) String term) {
        if (null!=term && !term.isBlank()) {
            log.info("Request Param received : {}" , term);
            return new ResponseEntity<>(blogService.filterByTerm(term) , HttpStatus.OK);
        }
        return new ResponseEntity<>(blogService.findAllBlogs() , HttpStatus.OK);
    }

    @GetMapping("/posts/{post_id}")
    public ResponseEntity<BlogResponseDTO> getBlogPostById(@PathVariable Long post_id) {
        return new ResponseEntity<>(blogService.findBlogPostById(post_id) , HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<BlogResponseDTO> addBlogPosts(@RequestBody @Valid BlogPostDTO blog) {
        BlogResponseDTO blogResponse = blogService.addBlog(blog);
        return new ResponseEntity<>(blogResponse , HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<String> deleteBlogPostById(@PathVariable Long post_id) {
        String response = blogService.deleteBlogPostById(post_id);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
