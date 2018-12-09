--liquibase formatted sql

--changeset teixeiradiego:1
CREATE SCHEMA IF NOT EXISTS client_list AUTHORIZATION client_list;


--rollback drop schema client_list;
