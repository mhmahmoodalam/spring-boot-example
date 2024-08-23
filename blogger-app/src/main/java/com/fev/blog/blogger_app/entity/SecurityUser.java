package com.fev.blog.blogger_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class SecurityUser {

    @Id
    @UuidGenerator
    private String id ;

    @Column(nullable = false)
    @Min(3)
    @Max(50)
    private String firstName;

    @Min(3)
    @Max(50)
    @Column(nullable = true)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    private String token;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private String bio;
    private String image;
    private boolean following;

    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private ZonedDateTime updatedAt;
}
