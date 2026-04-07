package com.vignesh.spring_blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
@ToString(exclude = "authProvider")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String userName;
    private String avatarUrl;
    private LocalDateTime createdAt;
    @NotEmpty
    private String name;

    @OneToMany( mappedBy = "user" , cascade = CascadeType.PERSIST)
    private List<UserAuthProvider> authProvider;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
