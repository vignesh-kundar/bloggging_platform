package com.vignesh.spring_blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BlogPostDTO (
        @NotBlank
        String title ,
        @NotBlank
        String content ,
        @NotBlank
        String category ,
        @NotEmpty
        List<String> tags) {}