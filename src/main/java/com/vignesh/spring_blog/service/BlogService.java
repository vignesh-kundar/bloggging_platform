package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.dto.BlogPostDTO;
import com.vignesh.spring_blog.dto.BlogResponseDTO;
import com.vignesh.spring_blog.entity.Blog;
import com.vignesh.spring_blog.entity.Tag;
import com.vignesh.spring_blog.repository.BlogRepository;
import com.vignesh.spring_blog.repository.TagRepository;
import com.vignesh.spring_blog.util.ResponseFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<BlogResponseDTO> findAllBlogs() {
        log.info("User requested for all Blogs!");
        List<Blog> blogs = blogRepository.findAll();
        log.debug("Blog List Fetched : {}" , blogs.stream().toList());
        return blogs.stream().map(ResponseFormatter::toResponse).toList();
    }

    public Blog addBlog(BlogPostDTO blog) {
        log.info("New Blog Entry received : {}" , blog.toString());

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

    public BlogResponseDTO findBlogPostById(Long postId) {
        Blog blog =  blogRepository.findById(postId).orElseThrow( () -> new NoSuchElementException("Blog Post with id " + postId + " was Not Found!"));
        return ResponseFormatter.toResponse(blog);
    }
}
