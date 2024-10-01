CREATE TABLE hospital
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    sido_id       BIGINT       NOT NULL,
    sigungu_id    BIGINT       NOT NULL,
    eupmundong_id BIGINT       NOT NULL,
    image_url     VARCHAR(100),
    address       VARCHAR(100) NOT NULL,
    name          VARCHAR(30)  NOT NULL,
    hours         VARCHAR(256),
    tel           VARCHAR(20)  NOT NULL,
    vet           VARCHAR(10)  NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);