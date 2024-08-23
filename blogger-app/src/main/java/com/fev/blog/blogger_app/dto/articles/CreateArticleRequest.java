package com.fev.blog.blogger_app.dto.articles;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonTypeName("article")
public class CreateArticleRequest {

    private String title  ;
    private String body ;
    private String description ;
    private List<String> tagList ;
}
