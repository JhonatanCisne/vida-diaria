CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users.users (
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(120) NOT NULL UNIQUE,
    nombres     VARCHAR(90)  NOT NULL,
    apellidos   VARCHAR(90)  NOT NULL,
    password    VARCHAR(255) NOT NULL,
    estado      VARCHAR(50)  NOT NULL,
    telefono    VARCHAR(20)  NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL
);
