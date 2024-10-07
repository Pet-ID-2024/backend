CREATE TABLE member_auth
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT       NOT NULL,
    name      VARCHAR(10)  NOT NULL,
    ssn       VARCHAR(255) NOT NULL,
    phone     VARCHAR(20)  NOT NULL,
    address   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);