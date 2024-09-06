CREATE TABLE hospital_hours
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    hospital_id   BIGINT      NOT NULL,
    day_of_week   VARCHAR(10) NOT NULL,
    opening_time  TIME,
    closing_time  TIME,
    breaking_time TIME,
    breaking_unit INT,
    is_closed     BOOLEAN DEFAULT FALSE
);