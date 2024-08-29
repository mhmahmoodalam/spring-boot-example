package com.fev.blog.blogger_app.dto.articles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fev.blog.blogger_app.dto.profiles.ProfileResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("article")
public class ArticleResponse {

    private String id ;
    private String slug ;
    private String title  ;
    private String body ;
    private String description ;
    private List<String> tagList ;
    private long favoritesCount ;
    private boolean favorited ;

    @JsonProperty("profile")
    private ProfileResponse author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
