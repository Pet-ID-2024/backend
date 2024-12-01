CREATE TABLE member_auth
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT       NOT NULL,
    name      VARCHAR(10)  NOT NULL,
    ssn       VARCHAR(255),
    phone     VARCHAR(20),
    address   VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);