# TALLER DE PATRONES ARQUITECTOS

### Requisitos Previos
Para ejecutar este proyecto, necesitarás tener instalado:

- Java JDK 8 o superior.
- Un IDE de Java como IntelliJ IDEA, Eclipse.
- Maven para manejar las dependencias 
- Un navegador web para interactuar con el servidor.

### Instalación 

1. Tener instalado Git en tu maquina local 
2. Elegir una carpeta en donde guardes tu proyecto
3. abrir la terminal de GIT --> mediante el clik derecho seleccionas Git bash here
4. Clona el repositorio en tu máquina local:
   ```bash
   git https://github.com/andreec2/Lab5-Arep.git
   ```
5. Abre el proyecto con tu IDE favorito o navega hasta el directorio del proyecto 
6. Desde la terminal  para compilar el proyecto ejecuta:

   ```bash
   mvn clean install
   ```
7. compila el proyecto  

   ```bash
    mvn clean package
   ```
8. Corra el servidor en la clase httpServer "main" o ejecute el siguiente comando desde consola
   
      ```bash
    java -cp target/classes com.example.DemoApplication
   ```

## Resumen del Proyecto

Este proyecto es una aplicación web básica para la gestión de propiedades inmobiliarias. Permite a los usuarios realizar las siguientes operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los listados de propiedades:

- **Crear nuevos listados de propiedades**: Los usuarios pueden agregar propiedades con información como dirección, precio, tamaño y descripción.
- **Leer las propiedades**: Los usuarios pueden ver todas las propiedades listadas o detalles de una propiedad individual.
- **Actualizar propiedades existentes**: Los usuarios pueden modificar la información de una propiedad ya existente.
- **Eliminar propiedades**: Los usuarios pueden eliminar propiedades del listado.

### Funcionalidades principales:
- Formulario para capturar información de la propiedad (dirección, precio, tamaño, descripción).
- Listado de propiedades con opciones para ver, actualizar y eliminar.
- Validación de formularios en el lado del cliente.
- Comunicación con el backend mediante AJAX o Fetch API.

## Arquitectura del Sistema

Este sistema se estructura en tres capas principales:

### 1. **Frontend (HTML + JavaScript)**

El frontend está compuesto por una interfaz web sencilla que permite a los usuarios interactuar con el sistema. Contiene:
- **Formularios** para capturar datos de las propiedades (dirección, precio, tamaño, descripción).
- **Lista de propiedades** que muestra todas las propiedades en la base de datos y proporciona opciones para ver, editar o eliminar cada propiedad.
- **Validación de formularios** en el lado del cliente para asegurar que los datos ingresados son correctos.
- **AJAX/Fetch API** para interactuar con los servicios REST del backend sin recargar la página.

### 2. **Backend (API RESTful en Spring Boot)**

El backend es una API RESTful desarrollada en **Spring Boot** que maneja las operaciones CRUD de las propiedades. Los endpoints son los siguientes:
- `POST /propiety`: Crear una nueva propiedad.
- `GET /propiety`: Recuperar todas las propiedades.
- `GET /propiety/{id}`: Obtener una propiedad por ID.
- `PUT /propiety/{id}`: Actualizar una propiedad existente.
- `DELETE /propiety/{id}`: Eliminar una propiedad.

La API está diseñada para manejar errores, como entradas no válidas o intentos de acceder a propiedades que no existen.

### 3. **Base de Datos (MySQL)**

La base de datos está implementada en **MySQL**. Contiene una tabla llamada `properties` con las siguientes columnas:
- `id`: Identificador único generado automáticamente.
- `address`: Dirección de la propiedad.
- `price`: Precio de la propiedad.
- `size`: Tamaño de la propiedad.
- `description`: Descripción de la propiedad.

La persistencia de los datos se maneja mediante **JPA/Hibernate**, lo que permite mapear los objetos Java a la base de datos y realizar las operaciones CRUD.

## Diseño de Clases

### Clases principales:

- **Property**: Representa una propiedad inmobiliaria.
  - Atributos: `id`, `address`, `price`, `size`, `description`.
  - Métodos: Getters y setters para los atributos.
 
![image](https://github.com/user-attachments/assets/189a6c32-76f9-4913-8aed-0998c3129bf7)


- **PropertyService**: Contiene la lógica de negocio para manejar las operaciones CRUD sobre las propiedades.
  - Métodos:
    - `createProperty(Property property)`
    - `getAllPropieties()`
    - `getPropertyById(Long id)`
    - `updateProperty(Long id, Property property)`
    - `deleteProperty(Long id)`

![image](https://github.com/user-attachments/assets/1372135d-79a8-40bb-aff7-2ced82a138d8)

- **PropertyController**: Es el controlador que maneja las peticiones HTTP y comunica las solicitudes con el `PropertyService`.
  - Métodos:
    - `createProperty()`
    - `getAllProperties()`
    - `getPropertyById()`
    - `updateProperty()`
    - `deleteProperty()`
   
![image](https://github.com/user-attachments/assets/9ecf68d7-9357-4d7e-abda-ce2b435f6dfb)


### Diagrama de clases:

### Instrucciones de implementación

Para crear una imagen de MySql en una maquina virtual EC2 en aws realizamos los siguientes pasos.

1. Creamos una nueva isntancia de EC2 y nos conectamos a ella mediante la clave ssh, luego creamos una base de datos en una imagen de docker con el siguiente comando 

   ```bash
   docker run --name miBase -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=propiedades -p 3306:3306 -d mysql:latest
   ```
Con esto se creara una Db de nombre propiedades.

![image](https://github.com/user-attachments/assets/efefd015-b985-48dd-a35e-874f1cc206fa)

Con la app funcionando en localHost realizamos algunos cambios para desplegarla en aws

1. Creacion de dockerFile.

![image](https://github.com/user-attachments/assets/4ce65144-b488-4778-b27f-72600b08d427)

2. Generamos la imagen de docker.

![image](https://github.com/user-attachments/assets/ae979fab-3c10-4fb4-90f3-3a8dd81afcd4)

3. Subimos la imagen de docker a docker Hub, para eso primero nos debemos loguear en docker hub.

![image](https://github.com/user-attachments/assets/42ca2690-7c7c-46cd-9dcb-6af5b34c4298)

![image](https://github.com/user-attachments/assets/202a1850-4724-4244-a44d-78d8ee623267)

4. la subimos/corremos en una nueva instancia de EC2 dentro de aws

   ```bash
   docker run -d -p 8080:8080 --name primeraimagedockeraws andreec22/laboratorio5
   ```
![image](https://github.com/user-attachments/assets/c7eaf789-e6ff-4f9c-ad99-71bd6496ba14)

5. Por ultimo configuramos los puertos de seguridad para permitir el trafico por los puertos 8080 y 3306.

![image](https://github.com/user-attachments/assets/f07b260e-f6c1-4bad-9543-fa6544056050)

6. Asi se ve la app desplegada en AWS.

![image](https://github.com/user-attachments/assets/91203b01-c9ac-4607-b9b5-fdd916cef5e4)

## Ejecutar las pruebas

Se implementaron pruebas unitarias para los métodos de manejo de solicitudes HTTP  en el servidor. Estas pruebas se realizaron utilizando JUnit  para simular las solicitudes y validar las respuestas.

Para ejecutar las pruebas:  
1. Desde tu IDE, ejecuta las clase AppTest.java o desde la terminal ejecutas:
   ```bash
   mvn test
   ```

![image](https://github.com/user-attachments/assets/a3c06579-6e7c-442e-a963-fd487a81d53d)

Aclaracion si la maquina virtual que tiene desplegada la base de datos no esta prendida el proyecto nisiquiera va a arrancar, aunque este en localHost, para comprobar el funcionamiento 
de las pruebas se debe cambiar en el application.propierties la conexion a la base de datos por una imagen local de docker y generarla mediante el siguiente comando.

   ```bash
   docker run --name miBase -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=propiedades -p 3306:3306 -d mysql:latest
   ```
### Videito corriendo las pruebas mientras EC2 esta corriendo. :)

https://pruebacorreoescuelaingeduco.sharepoint.com/sites/ieti410/_layouts/15/stream.aspx?id=%2Fsites%2Fieti410%2FShared%20Documents%2FGeneral%2FRecordings%2FReuni%C3%B3n%20en%20%5FGeneral%5F%2D20250306%5F232645%2DGrabaci%C3%B3n%20de%20la%20reuni%C3%B3n%2Emp4&referrer=StreamWebApp%2EWeb&referrerScenario=AddressBarCopied%2Eview%2E26eb348e%2D5e17%2D4712%2Dbf20%2D8e9e6570a8b7

### Video Despliegue

https://pruebacorreoescuelaingeduco.sharepoint.com/sites/ieti410/Shared%20Documents/General/Recordings/Meeting%20in%20_General_-20250306_185550-Meeting%20Recording.mp4?web=1&referrer=Teams.TEAMS-ELECTRON&referrerScenario=MeetingChicletExpiration.view

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Andres felipe montes ortiz** - 

