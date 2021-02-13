# Proyecto Web Java
Este proyecto consiste en una aplicación web Java EE con servlets para acceder a 
- Base de Datos
- FTP
- CDN (Apache)

## Configuración
### Base de Datos
Se debe crear un recurso "jdbc/poolBaseDatos" que contendrá los parámetros de conexión.
La Base de Datos deberá popularse con la siguiente estructura:

	CREATE TABLE PERSONAS (
		DNI VARCHAR(8) DEFAULT '00000000' NOT NULL,
		NOMBRE VARCHAR(20),
		APELLIDOS VARCHAR(20),
		PRIMARY KEY (dni)
	);
### FTP
Se deberá crear un recurso "jndi/FTP" que contendrá la URL del servidor FTP con el que trabajar

## Librerías
### Base de Datos
Para la conexión a Base de Datos se requieren las librerías Java correspondientes a la Base de Datos en cuestión.
- Para MySQL y MariaDB se deberá utilizar: [https://mvnrepository.com/artifact/mysql/mysql-connector-java](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- Para Oracle se deberá utilizar: [https://mvnrepository.com/artifact/oracle/oracle-jdbc](https://mvnrepository.com/artifact/oracle/oracle-jdbc)
- Para otras Bases de Datos se deberá utilizar el JDBC correspondiente

## Despliegue
1. Se deberán publicar las librerías necesarias en la carpeta /lib del Servidor de Aplicaciones
1. Se deberá configurar el recurso "jdbc/poolBaseDatos" en el Contexto del Servidor de Aplicaciones
1. Se deberá desplegar el WAR del proyecto en el Servidor de Aplicaciones