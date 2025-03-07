# TALLER DE PATRONES ARQUITECTOS

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

- **PropertyService**: Contiene la lógica de negocio para manejar las operaciones CRUD sobre las propiedades.
  - Métodos:
    - `createProperty(Property property)`
    - `getAllPropieties()`
    - `getPropertyById(Long id)`
    - `updateProperty(Long id, Property property)`
    - `deleteProperty(Long id)`

- **PropertyController**: Es el controlador que maneja las peticiones HTTP y comunica las solicitudes con el `PropertyService`.
  - Métodos:
    - `createProperty()`
    - `getAllProperties()`
    - `getPropertyById()`
    - `updateProperty()`
    - `deleteProperty()`

### Diagrama de clases:

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

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Video 



## Authors

* **Andres felipe montes ortiz** - 

