--liquibase formatted sql
--changeset admin:2
ALTER SEQUENCE weather_id_seq
INCREMENT 10
--rollback ALTER SEQUENCE weather_id_seq INCREMENT 1