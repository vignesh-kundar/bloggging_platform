package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.DTO.BlogPostDTO;
import com.vignesh.spring_blog.DTO.BlogResponseDTO;
import com.vignesh.spring_blog.entity.Blog;
import com.vignesh.spring_blog.entity.Tag;
import com.vignesh.spring_blog.repository.BlogRepository;
import com.vignesh.spring_blog.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    private BlogResponseDTO toResponse(Blog blog) {
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

    public List<BlogResponseDTO> findAllBlogs() {
        log.info("User requested for all Blogs!");
        List<Blog> blogs = blogRepository.findAll();
        log.debug("Blog List Fetched : {}" , blogs.stream().toList());
        List<BlogResponseDTO> response = blogs.stream().map(this::toResponse).toList();
        return response;
    }

    public Blog addBlog(BlogPostDTO blog) {

        List<Tag> tags = blog.tags().stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElse(tagRepository.save(Tag.builder().name(tagName).build()))).toList();

        Blog newBlog = Blog.builder()
                .title(blog.title())
                .category(blog.category())
                .content(blog.content())
                .tags(tags).build();

        return blogRepository.save(newBlog);
    }
}
