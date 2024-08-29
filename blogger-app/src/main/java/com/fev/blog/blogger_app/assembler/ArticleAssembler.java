package com.fev.blog.blogger_app.assembler;

import com.fev.blog.blogger_app.dto.articles.ArticleResponse;
import com.fev.blog.blogger_app.entity.Article;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleAssembler {

    private final ModelMapper modelMapper;

    @Autowired
    public ArticleAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        // addMappingConfiguration();
    }

    private void addMappingConfiguration() {
        this.modelMapper.createTypeMap(Article.class, ArticleResponse.class);
//                .addMappings( mapper -> {
//                    mapper.map(Article::getAuthor, ArticleResponse::setAuthor);
//
//                });
    }

    public ArticleResponse convertArticleResponseFrom(Article article) {
        return modelMapper.map(article, ArticleResponse.class);
    }
}
