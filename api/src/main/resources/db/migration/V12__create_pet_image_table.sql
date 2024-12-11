CREATE TABLE pet_image
(
    pet_image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pet_id       BIGINT,
    image_path   VARCHAR(255),
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NOT NULL
);
