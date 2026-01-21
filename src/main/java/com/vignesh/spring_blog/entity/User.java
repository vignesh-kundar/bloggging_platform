package com.vignesh.spring_blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record User(@Id Long id , String userId , String userName , String emailAddress , Long phoneNumber ) {
}