CREATE TABLE customers(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    cibil_score INT NOT NULL,
    score_fetched_at TIMESTAMP
);