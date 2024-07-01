CREATE TABLE hospital_order
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id       BIGINT       NOT NULL,
    hospital_id    BIGINT       NOT NULL,
    date TIMESTAMP NOT NULL,
    status     VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);