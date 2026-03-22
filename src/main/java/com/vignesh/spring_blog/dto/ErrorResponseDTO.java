package com.vignesh.spring_blog.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO(HttpStatus status , String errorMesssage , LocalDateTime timeStamp) {}