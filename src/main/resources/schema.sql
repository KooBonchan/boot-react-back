CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO car (name) VALUES ('fender'), ('sire');