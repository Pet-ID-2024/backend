CREATE TABLE pet_appearance
(
    appearance_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pet_id        BIGINT,
    breed         VARCHAR(255),
    hair_color    VARCHAR(255),
    weight        INT,
    hair_length   VARCHAR(255),
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NOT NULL
);
