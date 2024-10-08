package com.fev.blog.blogger_app.controllers;

import com.fev.blog.blogger_app.dto.articles.ArticleResponse;
import com.fev.blog.blogger_app.dto.articles.ArticlesSearchRequest;
import com.fev.blog.blogger_app.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticlesController {

    private ArticleService articleService;

    /**
     * {
     * "articles": [
     * {
     * "slug": "string",
     * "title": "string",
     * "description": "string",
     * "body": "string",
     * "tagList": [
     * "string"
     * ],
     * "createdAt": "2024-08-22T10:17:13.453Z",
     * "updatedAt": "2024-08-22T10:17:13.453Z",
     * "favorited": true,
     * "favoritesCount": 0,
     * "author": {
     * "username": "string",
     * "bio": "string",
     * "image": "string",
     * "following": true
     * }
     * }
     * ],
     * "articlesCount": 0
     * }
     */
    // /articles/feed?offset=1&limit=20
    @GetMapping("/feed")
    public Page<ArticleResponse> getArticleFeeds(@RequestParam(name = "offset") final int offset,
                                                 @RequestParam(name = "limit") final int limit) {
        var pageable = PageRequest.of(offset, limit);
        return articleService.getArticleFeeds(pageable);
    }

    /**
     * {
     * "articles": [],
     * "articlesCount": 0
     * }
     */
    // /articles?tag=sdf&author=sd&favorited=sdf&offset=1&limit=20
    @GetMapping()
    public Page<ArticleResponse> searchArticles(@RequestParam("tag") String tag,
                                                @RequestParam("author") String author,
                                                @RequestParam("favorited") boolean favorited,
                                                @RequestParam("offset") int offset,
                                                @RequestParam("limit") int limit) {

        return articleService.searchArticles(new ArticlesSearchRequest(tag, author, favorited, offset, limit));
    }
    /**
     * {
     *   "article": {
     *     "title": "string",
     *     "description": "string",
     *     "body": "string",
     *     "tagList": [
     *       "string"
     *     ]
     *   }
     * }
     */
    // POST /articles
//    @PostMapping("/articles")
//    public Page<ArticleResponse> postArticle(@RequestBody CreateArticleRequest createArticleRequest) {
//
//        return articleService.postArticle(createArticleRequest);
//    }
    /**
     * {
     *   "article": {
     *     "slug": "string",
     *     "title": "string",
     *     "description": "string",
     *     "body": "string",
     *     "tagList": [
     *       "string"
     *     ],
     *     "createdAt": "2024-08-22T10:14:52.427Z",
     *     "updatedAt": "2024-08-22T10:14:52.427Z",
     *     "favorited": true,
     *     "favoritesCount": 0,
     *     "author": {
     *       "username": "string",
     *       "bio": "string",
     *       "image": "string",
     *       "following": true
     *     }
     *   }
     * }
     */
    // GET /articles/{slug}

    /**
     * {
     *   "article": {
     *     "title": "string",
     *     "description": "string",
     *     "body": "string"
     *   }
     * }
     */
    // PUT /articles/{slug}

    // DELETE /articles/{slug}
}
