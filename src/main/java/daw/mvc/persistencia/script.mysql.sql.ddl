CREATE TABLE personas (
	dni VARCHAR(255) DEFAULT '' NOT NULL,
	nombre VARCHAR(255),
	apellidos VARCHAR(255),
	PRIMARY KEY (dni)
) ENGINE=MyISAM;

insert into `personas` (`dni`, `nombre`, `apellidos`) values('50000001', 'fer', 'redondo')
insert into `personas` (`dni`, `nombre`, `apellidos`) values('1234567', 'ana', 'perez')

