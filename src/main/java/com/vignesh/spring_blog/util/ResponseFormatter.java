package com.vignesh.spring_blog.util;

import com.vignesh.spring_blog.dto.BlogResponseDTO;
import com.vignesh.spring_blog.dto.BlogResponseDTOV2;
import com.vignesh.spring_blog.entity.Blog;
import com.vignesh.spring_blog.entity.Tag;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseFormatter {

    public static BlogResponseDTO toResponse(Blog blog) {
        return new BlogResponseDTO(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getCategory(),
                blog.getTags().stream().map(Tag::getName).toList(),
                blog.getCreatedAt(),
                blog.getUpdatedAt()
        );
    }

    public static BlogResponseDTOV2 toResponseV2(Blog blog) {
        return new BlogResponseDTOV2(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getCategory(),
                blog.getTags().stream().map(Tag::getName).toList(),
                blog.getCreatedAt(),
                blog.getUpdatedAt(),
                blog.getUsers().getUserName()
        );
    }

}
