CREATE DATABASE PROYECTO;

USE PROYECTO;

CREATE TABLE PERSONAS (
	DNI VARCHAR(8) DEFAULT '00000000' NOT NULL,
	NOMBRE VARCHAR(20),
	APELLIDOS VARCHAR(20),
	PRIMARY KEY (dni)
);