CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL
);

CREATE TABLE users_articles (
    user_id BIGINT NOT NULL,
    article_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, article_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (article_id) REFERENCES articles(id)
);
