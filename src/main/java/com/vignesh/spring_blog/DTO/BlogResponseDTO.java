package com.vignesh.spring_blog.DTO;

import com.vignesh.spring_blog.entity.Tag;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BlogResponseDTO(Long id, String title, String content , String category , List<String> tags , LocalDateTime createdAt , LocalDateTime updatedAt) {}