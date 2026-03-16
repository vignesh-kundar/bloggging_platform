package com.vignesh.spring_blog.dto;

import java.util.List;

public record BlogPostDTO ( String title , String content , String category , List<String> tags) {}