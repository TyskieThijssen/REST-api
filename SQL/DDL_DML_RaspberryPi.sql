DROP DATABASE IF EXISTS RaspberryPi;

CREATE DATABASE RaspberryPi;

USE RaspberryPi;

CREATE TABLE Person(
	id		int			NOT NULL,
	name	varchar(50)	NOT NULL,
    age		int			NOT NULL
);

INSERT INTO Person VALUES
	(1, 'Thijs', 20),
    (2, 'Sanne', 17)