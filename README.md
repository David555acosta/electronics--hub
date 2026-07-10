# Electronics Hub - Product Microservice

Este proyecto es un microservicio modular desarrollado en **Java 21** y **Spring Boot 3** enfocado en la gestión avanzada de productos y sus detalles técnicos. La arquitectura del sistema sigue los principios de **Arquitectura Hexagonal (Ports & Adapters)** y **DDD (Domain-Driven Design)**, garantizando un desacoplamiento absoluto entre la lógica de negocio, los contratos de la API y los mecanismos de persistencia.

---

## 📂 Estructura del Proyecto (Árbol de Directorios)

```text
electronics--hub [expecializacion]
├── .idea/
├── .mvn/
├── docker-config/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/curso/expecializacion/
│       │       ├── producDetail/
│       │       │   ├── domain/
│       │       │   │   └── ProductDetail.java
│       │       │   └── infraestructure/
│       │       │       └── ProductDetailEntity.java
│       │       └── product/
│       │           ├── application/
│       │           │   └── command/
│       │           │       ├── config/
│       │           │       │   ├── ApplicationConfig.java
│       │           │       │   └── OpenApiConfig.java
│       │           │       ├── create/
│       │           │       │   ├── ProductCreateHandler.java
│       │           │       │   ├── ProductCreateRequest.java
│       │           │       │   └── ProductCreateResponse.java
│       │           │       ├── delete/
│       │           │       │   ├── DeleteCreateHandler.java
│       │           │       │   └── DeleteProductRequest.java
│       │           │       └── update/
│       │           │           ├── UpdateProductCreateHandler.java
│       │           │           └── UpdateProductCreateRequest.java
│       │           ├── domain/
│       │           │   ├── Product.java
│       │           │   ├── ProductFilter.java
│       │           │   └── product_repository.java
│       │           └── infraestructure/
│       │               ├── api/
│       │               │   ├── dto/
│       │               │   │   ├── CreateProductDTO.java
│       │               │   │   ├── ProductDTO.java
│       │               │   │   └── UpdateProductDTO.java
│       │               │   ├── mapper/
│       │               │   │   └── ProductMapper.java
│       │               │   ├── product_api.java
│       │               │   └── ProductController.java
│       │               └── database/
│       │                   ├── entity/
│       │                   │   ├── ProductEntity.java
│       │                   │   ├── ProductEntityRepository.java
│       │                   │   └── ProductSpecificationEntity.java
│       │                   ├── mapper/
│       │                   │   └── ProductoEntityMapper.java
│       │                   ├── repositoryDBProducts/
│       │                   │   └── QueryProductsRepository.java
│       │                   ├── seeder/
│       │                   │   └── ProductSeeder.java
│       │                   └── ProductoRepository.java
│       └── resources/
│           ├── products.json
│           └── application.yml
├── .dockerignore
├── .gitattributes
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── expecializacion.iml
├── mvnw
├── mvenw.cmd
├── pom.xml
└── README.md

🛠️ Stack Tecnológico y Versiones Relacionadas
Java 21: JDK base aprovechando las últimas mejoras de rendimiento de la JVM.

Spring Boot 3.3.2: Core del framework base corporativo.

Spring Data JPA & Hibernate (Spring Boot Starter): Abstracción de datos ORM encargada de traducir el grafo de objetos hacia el modelo relacional de forma asincrónica o perezosa.

PostgreSQL 17 (pgvector/pgvector:pg17): Motor de base de datos relacional nativo con soporte de vectores integrado.

Jackson (ObjectMapper): Motor core de serialización/deserialización encargado de parsear estructuras JSON complejas al arrancar el contexto de Spring.

MapStruct 1.5.5.Final: Framework de mapeo de tipos fuertemente tipado en tiempo de compilación. Procesa las conversiones aislando las entidades de base de datos (ProductEntity) del Dominio Puro (Product) y la API (ProductDTO) mediante su procesador de anotaciones acoplado a Lombok.

Lombok 1.18.34: Librería de automatización de código boilerplate (Getters, Setters, Builders).

Springdoc OpenAPI UI 2.5.0: Motor automatizado de generación de documentación OpenAPI / Swagger.

Spring Boot Actuator: Monitorización de métricas de salud del sistema expuestas en endpoints locales.

🚀 Guía de Uso y Despliegue Local
Prerrequisitos
Asegúrate de tener instalados los siguientes componentes en tu entorno local:

Docker Desktop

Java 21 (JDK instalado y configurado en el PATH)

Maven 3.x o utilizar el wrapper integrado (./mvnw)

Paso 1: Levantar la Infraestructura (Base de Datos)
El proyecto orquesta una imagen optimizada de PostgreSQL. Ejecuta el siguiente comando en la raíz del proyecto para iniciar la base de datos en segundo plano:

Bash
docker-compose up -d
Nota: Esto aprovisionará un volumen persistente llamado postgres_data y ejecutará scripts iniciales desde ./docker-config/database/init.sql si existiesen.

Paso 2: Configuración del Entorno (application.yml)
El archivo de propiedades está configurado para desarrollo continuo:

spring.jpa.hibernate.ddl-auto: create-drop limpia, destruye y aprovisiona las tablas automáticamente en cada ciclo de arranque/parada.

El componente ProductSeeder mapeará automáticamente el archivo products.json al inicializar el contexto mediante Jackson, inyectando los datos semilla relacionales (OneToOne) de forma limpia en disco.

spring.cache.type: none desactiva la caché en memoria en caliente para garantizar respuestas HTTP no cacheadas.

Nota técnica de seguridad: Asegúrate de borrar o vaciar por completo cualquier archivo application.properties huérfano para evitar colisiones en la precedencia de configuraciones de Spring Boot.

Paso 3: Compilar y Ejecutar el Microservicio
Limpia los artefactos de compilación anteriores (obligatorio para que el plugin de Maven regenere los mappers implícitos de MapStruct generados en la carpeta /target) y arranca la aplicación:

Bash
# Limpieza de empaquetado anterior
./mvnw clean

# Ejecución del servidor de Spring Boot
./mvnw spring-boot:run
El servidor levantará en el puerto configurado: 9526.

Paso 4: Consumir la API
Puedes verificar el correcto funcionamiento del pipeline, la paginación, los filtros dinámicos basados en JPA Specifications y la correcta hidratación relacional del campo provider consumiendo el endpoint principal desde Postman o cURL:

HTTP
GET http://localhost:9526/api/products
Para validar y probar interactivamente el contrato de los DTOs y los esquemas expuestos por los controladores, accede a la consola de Swagger UI:

HTTP
http://localhost:9526/swagger-ui.html
