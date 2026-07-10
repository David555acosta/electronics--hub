
# Electronics Hub

Backend REST API desarrollada con Java y Spring Boot para la gestión de productos electrónicos. El proyecto sigue una arquitectura por capas, aplicando principios de Clean Architecture y Domain-Driven Design (DDD), con persistencia mediante Spring Data JPA y Hibernate.

## Características

- CRUD completo de productos.
- Arquitectura basada en capas.
- Separación entre dominio e infraestructura.
- Spring Data JPA + Hibernate.
- Mapeo con MapStruct.
- Validaciones.
- Manejo centralizado de excepciones.
- Cache con Spring Cache.
- Base de datos PostgreSQL.
- Seeders para carga inicial de datos.
- Relación One-to-One entre Producto y Detalle del Producto.

## Tecnologías

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- MapStruct
- Lombok
- Maven
- Docker (en proceso)

## Arquitectura

```
Controller
    │
Application
    │
Domain
    │
Infrastructure
    │
PostgreSQL
```

## Modelo de datos

Producto

- Código
- Nombre
- Descripción
- Precio
- Imagen

Detalle del producto

- Especificaciones
- Garantía
- Proveedor

Relación:

```
Product
    │
    └────── 1 : 1 ────── ProductDetail
```

## Próximas funcionalidades

- Categorías
- Reviews de productos
- Autenticación JWT
- Docker Compose
- Tests unitarios
- Tests de integración
- Documentación con Swagger/OpenAPI
- Redis para cache
- CI/CD con GitHub Actions

## Cómo ejecutar

```bash
git clone https://github.com/David555acosta/electronics--hub.git

cd electronics--hub

./mvnw spring-boot:run
```

## Estado del proyecto

🚧 En desarrollo.
