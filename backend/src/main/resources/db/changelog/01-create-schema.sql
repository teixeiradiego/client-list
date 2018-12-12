--liquibase formatted sql

--changeset teixeiradiego:1
CREATE SCHEMA IF NOT EXISTS contact_list AUTHORIZATION contact_list;


--rollback drop schema contact_list;
