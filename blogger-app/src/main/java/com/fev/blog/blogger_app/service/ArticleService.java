package com.fev.blog.blogger_app.service;

import com.fev.blog.blogger_app.assembler.ArticleAssembler;
import com.fev.blog.blogger_app.dto.articles.ArticleResponse;
import com.fev.blog.blogger_app.dto.articles.ArticlesSearchRequest;
import com.fev.blog.blogger_app.entity.Article;
import com.fev.blog.blogger_app.repository.ArticleRepository;
import com.fev.blog.blogger_app.repository.ArticleSpecficiations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleAssembler articleAssembler;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleAssembler articleAssembler) {
        this.articleRepository = articleRepository;
        this.articleAssembler = articleAssembler;
    }

    public Page<ArticleResponse> getArticleFeeds(Pageable pageable) {
        var articlesList= articleRepository.findAll(pageable);
        var reponseList = articlesList.stream().map(articleAssembler::convertArticleResponseFrom).toList();
        return new PageImpl<>(reponseList,pageable,articlesList.getTotalElements());
    }

    public Page<ArticleResponse> searchArticles(ArticlesSearchRequest articlesSearchRequest) {
        var pageable = PageRequest.of(articlesSearchRequest.getOffset(),articlesSearchRequest.getLimit());
        var articlesList= articleRepository.findAll(ArticleSpecficiations.authoredBy(articlesSearchRequest.getAuthor())
                .and(ArticleSpecficiations.containsTags(articlesSearchRequest.getTag())
                        .and(ArticleSpecficiations.isFavourated(articlesSearchRequest.isFavorited()))),pageable);
        var reponseList = articlesList.stream().map(articleAssembler::convertArticleResponseFrom).toList();
        return new PageImpl<>(reponseList,pageable,articlesList.getTotalElements());
    }
}
