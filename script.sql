CREATE DATABASE restaurante;
USE restaurante;
CREATE TABLE usuario (
	nombre VARCHAR(255),
	apellido VARCHAR(255),
	dni VARCHAR(9) PRIMARY KEY
);

CREATE TABLE mesa (
	numMesa INT PRIMARY KEY,
	ubicacion VARCHAR(255),
	numAsientos INT
);

CREATE TABLE reservas (
	numMesa INT,
	dniUsuario VARCHAR(9),
	fecha DATE,
	numPersonas INT,	
	FOREIGN KEY (numMesa) REFERENCES mesa(numMesa),
	FOREIGN KEY (dniUsuario) REFERENCES usuario(dni),
	PRIMARY KEY (numMesa, dniUsuario, fecha)
);

CREATE USER 'adminRestaurante'@'localhost' IDENTIFIED BY '12345';

GRANT ALL PRIVILEGES ON restaurante.* TO 'adminRestaurante'@'localhost';

FLUSH PRIVILEGES;