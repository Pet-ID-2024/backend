CREATE TABLE hospital_entity_image_url
(
    hospital_entity_id BIGINT,
    image_url   VARCHAR(255),
    PRIMARY KEY (hospital_entity_id, image_url),
    FOREIGN KEY (hospital_entity_id) REFERENCES hospital (id) ON DELETE CASCADE
);
