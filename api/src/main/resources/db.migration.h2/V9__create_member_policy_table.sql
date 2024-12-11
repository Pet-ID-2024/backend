CREATE TABLE member_policy
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id     BIGINT    NOT NULL,
    advertisement BOOLEAN,
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NOT NULL
);