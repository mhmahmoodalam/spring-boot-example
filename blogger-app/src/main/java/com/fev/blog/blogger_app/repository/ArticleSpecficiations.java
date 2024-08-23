package com.fev.blog.blogger_app.repository;

import com.fev.blog.blogger_app.entity.Article;
import com.fev.blog.blogger_app.entity.SecurityUser;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ArticleSpecficiations {

    public static Specification<Article> containsTags(String tags) {
        return (root, query, builder) -> {
            final Path<List<String>> tagsExpression = root.<List<String>>get("tagList");
            return builder.isMember(tags, tagsExpression);
        };
    }


    public static Specification<Article> authoredBy(String authorId) {
        return (root, query, builder) -> {
            final Path<String> userId = root.<SecurityUser>get("author").<String>get("id");
            return builder.equal(userId,authorId);
        };
    }

    public static Specification<Article> isFavourated(boolean isFovourated) {
        return (root, query, builder) -> {
            final Path<Boolean> group = root.<Boolean>get("favorited");
            return builder.equal(group,isFovourated);
        };
    }


}
