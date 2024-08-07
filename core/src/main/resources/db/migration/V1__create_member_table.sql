CREATE TABLE member
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    uid      VARCHAR(100) NOT NULL,
    platform VARCHAR(10)  NOT NULL,
    fcm_token VARCHAR(512),
    email    VARCHAR(100),
    role     VARCHAR(20),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);