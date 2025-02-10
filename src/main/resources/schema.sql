CREATE TABLE owner(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name varchar(63),
  last_name varchar(63)
);


CREATE TABLE car (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(63) UNIQUE,
  brand VARCHAR(63) NOT NULL,
  model VARCHAR(63) NOT NULL,
  color VARCHAR(63) NOT NULL,
  registration_number VARCHAR(255) NOT NULL,
  model_year INT NOT NULL,
  price INT NOT NULL,
  owner_id BIGINT REFERENCES owner(id)
);

CREATE TABLE app_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(63) UNIQUE,
  password VARCHAR(127) NOT NULL,
  role VARCHAR(63) DEFAULT 'USER' NOT NULL
);

INSERT INTO owner (first_name, last_name)
VALUES ('Koo', 'Bonchan'), ('Kook', 'Bonchank');

INSERT INTO car (name, brand, model, color, registration_number, model_year, price, owner_id)
VALUES
('Thunderstrike', 'Fender', 'Precision Bass', 'Sunburst', 'FEN123456789', 2024, 799.99, 1),
('Dark Knight', 'Ibanez', 'SR305', 'Black', 'IBA987654321', 2023, 549.99, 1),
('EchoMaster', 'Music Man', 'StingRay 5', 'Blue Sparkle', 'MM112233445', 2022, 1499.99, 1),
('VibeMaster', 'Gibson', 'Thunderbird IV', 'White', 'GIB667788990', 2021, 1199.99, 2),
('BassBeast', 'Yamaha', 'BB734A', 'Natural Wood', 'YAM445566778', 2025, 999.99, 2);


INSERT INTO app_user (username, password, role)
values
('user',
 '$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9qe4bRadue',
 'USER'),
('admin',
 '$2a$10$l5di823ONuOSzt3I.LvDS.3jAC2ftS.MPJjQXLASYS1YFzkWOhRTG', -- admin
 'ADMIN');