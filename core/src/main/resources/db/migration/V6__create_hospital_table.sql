CREATE TABLE hospital
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    sigungu_id BIGINT      NOT NULL,
    address    VARCHAR(100) NOT NULL,
    name       VARCHAR(30) NOT NULL,
    hours      VARCHAR(30),
    tel        VARCHAR(20) NOT NULL,
    vet        VARCHAR(10) NOT NULL
);