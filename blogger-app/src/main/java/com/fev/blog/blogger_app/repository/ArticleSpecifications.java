package com.fev.blog.blogger_app.repository;

import com.fev.blog.blogger_app.entity.Article;
import com.fev.blog.blogger_app.entity.Profile;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ArticleSpecifications {

    public static Specification<Article> containsTags(String tags) {
        return (root, query, builder) -> {
            final Path<List<String>> tagsExpression = root.<List<String>>get("tagList");
            return builder.isMember(tags, tagsExpression);
        };
    }


    public static Specification<Article> authoredBy(String authorFirstName) {
        return (root, query, builder) -> {
            final Path<String> userId = root.<Profile>get("author").<String>get("firstName");
            return builder.equal(userId, authorFirstName);
        };
    }

    public static Specification<Article> isFavourated(boolean isFavourated) {
        return (root, query, builder) -> {
            final Path<Boolean> group = root.<Boolean>get("favorited");
            return builder.equal(group, isFavourated);
        };
    }


}
