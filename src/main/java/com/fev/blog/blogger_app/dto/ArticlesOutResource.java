package com.fev.blog.blogger_app.dto;

import com.fev.blog.blogger_app.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class ArticlesOutResource {

    private String id ;
    private String slug ;
    private String title  ;
    private String body ;
    private String description ;
    private List<String> tagList ;
    private long favoritesCount ;
    private boolean favorited ;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
