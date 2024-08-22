package com.fev.blog.blogger_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Length;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @UuidGenerator
    private String id ;

    private String slug ;

    @Column(nullable = false,length = Length.LONG)
    private String title  ;

    @Column(nullable = false,length = Length.LOB_DEFAULT)
    private String body ;

    @Column(nullable = true,length = Length.LONG)
    private String description ;

    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "articles_tag_list",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "tags", nullable = true)
    private List<String> tagList ;

    private long favoritesCount ;
    private boolean favorited ;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(nullable = false, name="author_id",referencedColumnName = "id")
    private User author;

    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private ZonedDateTime updatedAt;
}
