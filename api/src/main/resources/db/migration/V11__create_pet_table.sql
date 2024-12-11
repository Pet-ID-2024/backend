CREATE TABLE pet
(
    pet_id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    pet_reg_no        VARCHAR(255),
    pet_name          VARCHAR(255),
    pet_birth_date    VARCHAR(255),
    pet_sex           CHAR(1),
    pet_neutered_yn   CHAR(1),
    pet_neutered_date DATETIME,
    pet_addr          VARCHAR(255),
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NOT NULL
);
