package com.fev.blog.blogger_app.repository;

import com.fev.blog.blogger_app.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
}