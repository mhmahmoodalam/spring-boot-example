package com.fev.blog.blogger_app.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesController {

    /**
     * {
     *   "articles": [
     *     {
     *       "slug": "string",
     *       "title": "string",
     *       "description": "string",
     *       "body": "string",
     *       "tagList": [
     *         "string"
     *       ],
     *       "createdAt": "2024-08-22T10:17:13.453Z",
     *       "updatedAt": "2024-08-22T10:17:13.453Z",
     *       "favorited": true,
     *       "favoritesCount": 0,
     *       "author": {
     *         "username": "string",
     *         "bio": "string",
     *         "image": "string",
     *         "following": true
     *       }
     *     }
     *   ],
     *   "articlesCount": 0
     * }
     */
    // /articles/feed?offset=1&limit=20

    /**
     * {
     *   "articles": [],
     *   "articlesCount": 0
     * }
     */
    // /articles?tag=sdf&author=sd&favorited=sdf&offset=1&limit=20

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
