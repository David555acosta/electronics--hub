# Electronics Hub - Product Microservice

Este proyecto es un microservicio modular desarrollado en **Java 21** y **Spring Boot 3** enfocado en la gestión avanzada de productos y sus detalles técnicos. La arquitectura del sistema sigue los principios de **Arquitectura Hexagonal (Ports & Adapters)** y **DDD (Domain-Driven Design)**, garantizando un desacoplamiento absoluto entre la lógica de negocio, los contratos de la API y los mecanismos de persistencia.

---

## 📂 Estructura del Proyecto (Árbol de Directorios)

```text
electronics-hub [especializacion]
├── docker-config/                  # Scripts de inicialización de BD
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/curso/especializacion/
│   │   │       ├── category/               # Módulo de Categorías
│   │   │       │   ├── domain/
│   │   │       │   └── infrastructure/
│   │   │       │       ├── CategoryEntity.java
│   │   │       │       ├── CategoryEntityMapper.java
│   │   │       │       ├── CategoryRepository.java
│   │   │       │       └── CategorySeeder.java
│   │   │       ├── config/                 # Configuración Global, Excepciones y Seguridad
│   │   │       │   ├── domain/             # DTOs de Paginación genéricos
│   │   │       │   │   ├── PaginationQuery.java
│   │   │       │   │   └── PaginationResult.java
│   │   │       │   ├── exceptions/         # Global Exception Handler
│   │   │       │   │   ├── ApiExceptionHandler.java
│   │   │       │   │   └── ErrorMessage.java
│   │   │       │   ├── security/           # Núcleo de Seguridad Spring Security + JWT
│   │   │       │   │   ├── controllers/    # Test Rest Controllers para Roles
│   │   │       │   │   ├── filters/        # JwtAuthorizationFilter (Refresh Token Window)
│   │   │       │   │   ├── jwt/            # JwtUtils (Generación, firma y validación de tokens)
│   │   │       │   │   ├── services/       # UserDetailServiceImpl
│   │   │       │   │   └── SecurityConfig.java
│   │   │       │   ├── ApplicationConfig.java
│   │   │       │   └── OpenApiConfig.java  # Configuración Swagger UI + Bearer Auth
│   │   │       ├── productDetail/          # Módulo de Detalle de Productos
│   │   │       │   ├── domain/
│   │   │       │   └── infrastructure/
│   │   │       ├── product/                # Módulo Principal de Productos (CQRS + Mediator)
│   │   │       │   ├── application/
│   │   │       │   │   ├── command/        # Handlers y Requests de Escritura (Create, Update, Delete)
│   │   │       │   │   └── query/          # Handlers y Requests de Lectura (GetById, GetAll)
│   │   │       │   ├── common/
│   │   │       │   │   ├── mediator/       # Mediator y Handlers desacoplados
│   │   │       │   │   └── util/
│   │   │       │   ├── domain/             # Dominio Puro, Excepciones e Interfaz de Repositorio
│   │   │       │   └── infrastructure/
│   │   │       │       ├── api/            # Controller, DTOs y Mappers
│   │   │       │       └── database/       # JPA Entities, Specifications, Cache y Repositorio
│   │   │       ├── review/                 # Módulo de Reseñas / Reviews
│   │   │       │   ├── domain/
│   │   │       │   └── infrastructure/
│   │   │       ├── user/                   # Módulo de Usuarios y Autenticación
│   │   │       │   ├── domain/             # Excepciones personalizadas (401/403 Handlers)
│   │   │       │   └── infrastructure/     # Entidades de Usuario/Rol y Repositorios
│   │   │       └── EspecializacionApplication.java  # Clase principal
│   │   │
│   │   └── resources/
│   │       ├── application.yml             # Configuración base
│   │       ├── application-dev.yml         # Perfil desarrollo
│   │       ├── application-prod.yml        # Perfil producción
│   │       ├── application-test.yml        # Perfil pruebas (H2 / Test IT)
│   │       └── *.json                      # Seeders iniciales (categories, products, details)
│   │
│   └── test/
│       └── java/
│           └── com/curso/especializacion/
│               ├── product/                # Tests Unitarios de Repositorios y JPA Slices
│               └── TI/                     # Tests de Integración End-to-End con Security y JWT
│
├── Dockerfile                              # Imagen Docker multi-stage
├── docker-compose.yml                      # Orquestación de Spring Boot + Postgres
├── pom.xml                                 # Dependencias Maven
└── README.md

# Stack Tecnológico y Versiones Relacionadas
Java 21: JDK base aprovechando las últimas mejoras de rendimiento de la JVM.

Spring Boot 3.3.2: Core del framework base corporativo.

Spring Data JPA & Hibernate (Spring Boot Starter): Abstracción de datos ORM encargada de traducir el grafo de objetos hacia el modelo relacional de forma asincrónica o perezosa.

PostgreSQL 17 (pgvector/pgvector:pg17): Motor de base de datos relacional nativo con soporte de vectores integrado.

Jackson (ObjectMapper): Motor core de serialización/deserialización encargado de parsear estructuras JSON complejas al arrancar el contexto de Spring.

MapStruct 1.5.5.Final: Framework de mapeo de tipos fuertemente tipado en tiempo de compilación. Procesa las conversiones aislando las entidades de base de datos (ProductEntity) del Dominio Puro (Product) y la API (ProductDTO) mediante su procesador de anotaciones acoplado a Lombok.

Lombok 1.18.34: Librería de automatización de código boilerplate (Getters, Setters, Builders).

Springdoc OpenAPI UI 2.5.0: Motor automatizado de generación de documentación OpenAPI / Swagger.

Spring Boot Actuator: Monitorización de métricas de salud del sistema expuestas en endpoints locales.

# Guía de Uso y Despliegue Local
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
