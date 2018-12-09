--liquibase formatted sql

--changeset teixeiradiego:2

CREATE TABLE client_list.client (
	id SERIAL ,
	name VARCHAR(255) NOT NULL,
	photo_url TEXT NOT NULL,
	CONSTRAINT client_pk PRIMARY KEY (id)
)

--rollback DROP TABLE client_list.client;
