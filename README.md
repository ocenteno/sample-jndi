# Proyecto Web Java
Este proyecto consiste en una aplicación web Java EE con servlets para acceder a 
- Base de Datos
- FTP
- CDN (Apache)

El proyecto se encuentra en GitHub: [https://github.com/ocenteno/sample-jndi](https://github.com/ocenteno/sample-jndi)

## Configuración
### 1. Base de Datos
Se debe crear un recurso "jdbc/poolBaseDatos" que contendrá los parámetros de conexión.
La Base de Datos deberá popularse con la siguiente estructura:

	CREATE TABLE PERSONAS (
		DNI VARCHAR(8) DEFAULT '00000000' NOT NULL,
		NOMBRE VARCHAR(20),
		APELLIDOS VARCHAR(20),
		PRIMARY KEY (dni)
	);
	
### 2. Apache CDN
CDN es una forma habitual de separar el contenido estático de una aplicación web
Consiste en ubicarlo en un servidor virtual que se puede replicar para que cada usuario descargue ese contenido 
del servidor más cercano a su ubicación
La aplicación espera que exista un recurso "jndi/CDN" que apunte a la dirección web donde se han subido los ficheros estáticos
se deberá crear ese servidor virtual y el recurso correspondiente en el Servidor de Aplicaciones

### 3. FTP
Se deberá crear un recurso "jndi/FTP" que contendrá la URL del servidor FTP con el que trabajar
El servidor utiliza 2 usuarios con sus respectivas contraseñas:
- registrado:registrado para poder leer y obtener ficheros del servidor, debe tener permisos de lectura
- administrador:administrador para poder subir ficheros al servidor, debe tener permisos de escritura
La aplicación trabaja únicamente con el directorio raíz del servidor, no se implementará la descarga o la subida en otra carpeta

### 4. Opcional: DNS
Opcionalmente se podrá registrar nuestro servidor en un DNS (local o público) para poder hacer uso de URLs más representativas
para cada una de las piezas del sistema: Apache, FTP y Tomcat

## Librerías y ficheros
### 1. Base de Datos
Para la conexión a Base de Datos se requieren las librerías Java correspondientes a la Base de Datos en cuestión.
- Para MySQL y MariaDB se deberá utilizar: [https://mvnrepository.com/artifact/mysql/mysql-connector-java](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- Para Oracle se deberá utilizar: [https://mvnrepository.com/artifact/oracle/oracle-jdbc](https://mvnrepository.com/artifact/oracle/oracle-jdbc)
- Para otras Bases de Datos se deberá utilizar el JDBC correspondiente

## 2. Contenido
[Éste fichero](contenido.zip) "contenido.zip" contiene 
- Una imagen que deberá desplegarse en el servidor Apache
- Un video que deberá desplegarse en el servidor Apache
- El propio fichero contenido.zip deberá desplegarse en el servidor Apache

## 3. Aplicación
[Éste fichero](proyecto-java-ee.war) "proyecto-java-ee.war" contiene la aplicación Java que deberá desplegarse en el servidor de aplicaciones

## 4. FTP
Deberá hacerse accesible en el servidor FTP el PDF que explica la resolución de la práctica
y un fichero ZIP que contenga los ficheros de configuración que se han creado.
Además, se deberán añadir estos ficheros de configuración al repositorio GitHub que se ha utilizado para las actividades anteriores

## Despliegue
1. Se deberán publicar las librerías necesarias en la carpeta /lib del Servidor de Aplicaciones
1. Se deberá configurar el recurso "jdbc/poolBaseDatos" en el Contexto del Servidor de Aplicaciones
1. Se deberán configurar las variables de entorno "jndi/FTP" y "jndi/CDN" en el Contexto del Servidor de Aplicaciones
1. Se deberá desplegar el WAR del proyecto en el Servidor de Aplicaciones
1. Se deberán crear los usuarios "registrado" y "administrador" en el Servidor FTP
1. Se deberá configurar el Servidor Apache para poder acceder al directorio CDN y desplegar ahí los ficheros de prueba


