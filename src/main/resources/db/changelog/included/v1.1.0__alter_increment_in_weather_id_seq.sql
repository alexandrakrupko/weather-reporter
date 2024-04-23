--liquibase formatted sql
--changeset admin:alter-increment-in-weather-id-seq
ALTER SEQUENCE weather_id_seq
INCREMENT 10
--rollback ALTER SEQUENCE weather_id_seq INCREMENT 1