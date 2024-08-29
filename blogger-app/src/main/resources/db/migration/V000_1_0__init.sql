
DROP TABLE IF EXISTS "security_user" CASCADE ;
DROP TABLE IF EXISTS "authority" CASCADE ;
DROP TABLE IF EXISTS "users_authorities" CASCADE ;
DROP TABLE IF EXISTS "profile" CASCADE ;
DROP TABLE IF EXISTS "article" CASCADE ;
DROP TABLE IF EXISTS "articles_tag_list" CASCADE ;

CREATE TABLE security_user (
   id VARCHAR(255) NOT NULL,
   token VARCHAR(32600),
   security_user_name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_securityuser PRIMARY KEY (id)
);

CREATE TABLE authority (
  id VARCHAR(255) NOT NULL,
   name VARCHAR(255),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_authority PRIMARY KEY (id)
);


CREATE TABLE users_authorities (
  authority_id VARCHAR(255) NOT NULL,
   security_user_id VARCHAR(255) NOT NULL,
   CONSTRAINT pk_users_authorities PRIMARY KEY (authority_id, security_user_id)
);



CREATE TABLE profile (
  id VARCHAR(255) NOT NULL,
   security_user_id VARCHAR(255),
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255),
   email VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL,
   bio VARCHAR(255),
   image VARCHAR(255),
   following BOOLEAN NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_profile PRIMARY KEY (id)
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
  article_id VARCHAR(255) NOT NULL,
  tags VARCHAR(255)
);


ALTER TABLE users_authorities ADD CONSTRAINT fk_useaut_on_authority FOREIGN KEY (authority_id) REFERENCES authority (id);

ALTER TABLE users_authorities ADD CONSTRAINT fk_useaut_on_security_user FOREIGN KEY (security_user_id) REFERENCES security_user (id);

ALTER TABLE profile ADD CONSTRAINT uc_profile_security_user UNIQUE (security_user_id);

ALTER TABLE profile ADD CONSTRAINT FK_PROFILE_ON_SECURITY_USER FOREIGN KEY (security_user_id) REFERENCES security_user (id);

ALTER TABLE article ADD CONSTRAINT FK_ARTICLE_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES profile (id);

ALTER TABLE articles_tag_list ADD CONSTRAINT fk_articles_tag_list_on_article FOREIGN KEY (article_id) REFERENCES article (id);

