package com.vignesh.spring_blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vignesh.spring_blog.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

public record BlogResponseDTOV2 (Long id, String title, String content , String category , List<String> tags , LocalDateTime createdAt , LocalDateTime updatedAt , @JsonIgnore Users user) { }
