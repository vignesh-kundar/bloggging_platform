package com.vignesh.spring_blog.controller.v2;

import com.vignesh.spring_blog.dto.BlogPostDTO;
import com.vignesh.spring_blog.dto.BlogResponseDTO;
import com.vignesh.spring_blog.dto.BlogResponseDTOV2;
import com.vignesh.spring_blog.entity.Users;
import com.vignesh.spring_blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController(value = "blogControllerv2")
@RequestMapping("/api/v2")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/posts")
    public ResponseEntity<List<BlogResponseDTOV2>> getAllBlogPosts(@RequestParam(required = false) String term) {
        if (null!=term && !term.isBlank()) {
            log.info("Request Param received : {}" , term);
            return new ResponseEntity<>(blogService.filterByTermv2(term) , HttpStatus.OK);
        }
        return new ResponseEntity<>(blogService.findAllBlogsv2() , HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<BlogResponseDTO> addBlogPosts(@RequestBody @Valid BlogPostDTO blog) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BlogResponseDTO blogResponse = blogService.addBlog(blog , user);
        return new ResponseEntity<>(blogResponse , HttpStatus.CREATED);
    }

}
