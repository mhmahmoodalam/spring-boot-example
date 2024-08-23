package com.fev.blog.blogger_app.dto.articles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticlesSearchRequest {
    private String tag;
    private String author;
    private boolean favorited;
    private int offset;
    private int limit;
}
