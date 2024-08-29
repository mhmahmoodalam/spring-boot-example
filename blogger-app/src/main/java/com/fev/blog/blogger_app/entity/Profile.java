package com.fev.blog.blogger_app.entity;

import com.fev.blog.blogger_app.authentication.entity.SecurityUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Profile {

    @Id
    @UuidGenerator
    private String id ;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_user_id")
    private SecurityUser securityUser;

    @Column(nullable = false)
    @Length(min = 3,max = 50)
    private String firstName;


    @Column(nullable = true)
    @Length(min = 3,max = 50)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

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
