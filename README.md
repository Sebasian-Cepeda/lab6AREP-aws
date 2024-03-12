
# TALLER 6 PATRONES ARQUITECTURALES EN LA NUBE
# Descripción 
En este proyecto tendremos un servicio REST con spark y roundrobin el cúal por medio de contenedores docker correremos el servicio y luego esto lo haremos en AWS EC2.
## Video probando instancia EC2
https://github.com/Sebasian-Cepeda/lab6AREP-aws/assets/89321404/56589ee2-ec8d-4d79-8684-810c24b5dc86

## DESARROLLADO CON
* [docker](https://www.docker.com/products/docker-desktop/) contenedorización 
* [Java version 17](https://www.oracle.com/co/java/technologies/downloads/) - Lenguaje de programación usado.
* [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias del proyecto
* [Git](https://git-scm.com/downloads) - Gestion de versiones del proyecto

## Pasos para ejecutar
1. Debemos clonar este repositorio
```bash
git clone https://github.com/Sebasian-Cepeda/lab6AREP-aws.git
```
2. Hacemos un "cd" al repositorio clonado
3. Compilamos el proyecto con el siguiente comando
```bash
mvn clean install
```
4. Escribimos el siguiente comando para componer y asociar los contenedores en docker
```bash
docker-compose up -d
```
recordemos que para evitar cualquier problema con docker, es mejor tener los contenedores, imagenes y volumenes eliminados antes de hacer el compose

5. Accedemos a la siguiente URL: [localhost:4567](http://localhost:4567/) y obtenemos el siguiente resultado
   ![image](https://github.com/Sebasian-Cepeda/lab6AREP-aws/assets/89321404/508fb0cd-35d8-4c74-8221-9a0f70211f80)
   al iniciar por primera vez el proyecto, no tendra registros en la tabla por lo que al dar click al botón solo saldra ese nuevo registro que se acaba de ingresar y si el campo está vacio tendremos una alerta
  ![image](https://github.com/Sebasian-Cepeda/lab6AREP-aws/assets/89321404/d414456f-b531-4a1c-a5ca-f6e14a4c9aa3)

# Diseño
 El código proporcionado consiste en varias clases que forman un sistema para el manejo de logs utilizando Spark y MongoDB en un entorno de AWS.
 
Capa de presentación (Frontend):
Se encarga de la interfaz de usuario y la interacción con el usuario final.
En este caso, el frontend es una página web que permite al usuario ingresar un mensaje de registro y ver los registros existentes.
Utiliza HTML, CSS y JavaScript para crear la interfaz de usuario.
Interactúa con la capa de lógica de negocio a través de solicitudes HTTP.

Capa de lógica de negocio (Backend):
Se encarga de la lógica de la aplicación y el procesamiento de datos.
Contiene las clases Invoke, Mongo, LogServerFacade y LogServiceFacade.
LogServerFacade y LogServiceFacade actúan como controladores que manejan las solicitudes HTTP (con spark) entrantes y coordinan la lógica de negocio.
Mongo maneja la interacción con la base de datos MongoDB para almacenar y recuperar registros.
Invoke se utiliza para invocar otros servicios web y obtener respuestas.

Capa de datos (Base de datos):
Se encarga del almacenamiento persistente de los registros.
Utiliza MongoDB como base de datos para almacenar los registros de forma eficiente.
La clase Mongo se encarga de la interacción con la base de datos MongoDB.

## AUTOR
Juan Sebastian Cepeda saray



