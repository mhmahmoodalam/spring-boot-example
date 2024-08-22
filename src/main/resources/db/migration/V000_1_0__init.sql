DROP TABLE IF EXISTS "user" CASCADE ;
DROP TABLE IF EXISTS "article" CASCADE ;
DROP TABLE IF EXISTS "articles_tag_list" CASCADE ;

CREATE TABLE "user" (
  id VARCHAR(255) NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255),
   email VARCHAR(255) NOT NULL,
   token VARCHAR(255),
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   bio VARCHAR(255),
   image VARCHAR(255),
   following BOOLEAN NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE article (
  id VARCHAR(255) NOT NULL,
   slug VARCHAR(255),
   title VARCHAR(32600) NOT NULL,
   body VARCHAR(1048576) NOT NULL,
   description VARCHAR(32600),
   favorites_count BIGINT NOT NULL,
   favorited BOOLEAN NOT NULL,
   author_id VARCHAR(255) NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_article PRIMARY KEY (id)
);

CREATE TABLE articles_tag_list (
  id VARCHAR(255) NOT NULL,
   tags VARCHAR(255)
);

ALTER TABLE article ADD CONSTRAINT FK_ARTICLE_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES "user" (id);

ALTER TABLE articles_tag_list ADD CONSTRAINT fk_articles_tag_list_on_article FOREIGN KEY (id) REFERENCES article (id);