--liquibase formatted sql

--changeset teixeiradiego:2

CREATE TABLE contact_list.person (
	id SERIAL ,
	name VARCHAR(255) NOT NULL,
	photo_url TEXT NOT NULL,
	CONSTRAINT person_pk PRIMARY KEY (id)
);

GRANT ALL ON contact_list.person TO contact_list;

--rollback DROP TABLE contact_list.person;
