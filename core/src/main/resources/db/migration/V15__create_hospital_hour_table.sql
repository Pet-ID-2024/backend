CREATE TABLE hospital_hours
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    hospital_id  BIGINT NOT NULL,
    day_of_week          VARCHAR(10) NOT NULL,
    opening_time TIME,
    closing_time TIME,
    is_closed    BOOLEAN DEFAULT FALSE
);

ALTER TABLE hospital
    DROP COLUMN hours;