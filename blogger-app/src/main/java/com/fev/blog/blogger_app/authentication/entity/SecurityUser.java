package com.fev.blog.blogger_app.authentication.entity;

import com.fev.blog.blogger_app.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Length;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SecurityUser {

    @Id
    @UuidGenerator
    private String id ;

    @OneToOne(mappedBy = "securityUser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profile profile;

    @Column(nullable = true,length = Length.LONG)
    private String token;

    @Column(nullable = false)
    private String securityUserName;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private ZonedDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "security_user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> grantedAuthorities;




}
