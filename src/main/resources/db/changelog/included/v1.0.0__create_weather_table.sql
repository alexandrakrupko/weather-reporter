--liquibase formatted sql
--changeset admin:1
CREATE TABLE weather (
    id BIGSERIAL PRIMARY KEY, --sequence 'weather_id_seq' will be created with increment of 1
    city VARCHAR NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    actual FLOAT NOT NULL,
    feels_like FLOAT NOT NULL,
    rainfall VARCHAR NOT NULL
);
--rollback DROP TABLE weather