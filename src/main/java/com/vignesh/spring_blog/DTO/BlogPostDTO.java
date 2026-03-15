package com.vignesh.spring_blog.DTO;

import com.vignesh.spring_blog.entity.Tag;
import lombok.Data;

import java.util.List;

public record BlogPostDTO ( String title , String content , String category , List<String> tags) {}