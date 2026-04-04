package com.vignesh.spring_blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_auth_provider")
@Data
@Entity
public class UserAuthProvider {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id" , nullable = false)
    private Users user;

    @Column(nullable = false)
    private String provider;
    @Column(nullable = false)
    private String providerId;
    private String providerHash;
}
