# Tasks API 🗂️

REST API para gestión de tareas multi-usuario construida con Spring Boot.

## Tecnologías
- Java 21
- Spring Boot 3.4
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- Docker

## Endpoints
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/tasks | Listar todas las tareas |
| GET | /api/tasks/{id} | Obtener tarea por id |
| POST | /api/tasks | Crear tarea |
| PUT | /api/tasks/{id} | Actualizar tarea completa |
| PATCH | /api/tasks/{id}/status | Cambiar estado |
| DELETE | /api/tasks/{id} | Eliminar tarea |

## Correr el proyecto

### Base de datos
docker run --name tasks-db \
  -e POSTGRES_DB=tasksdb \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=password123 \
  -p 5432:5432 \
  -d postgres:16

### Aplicación
mvn spring-boot:run

## En progreso
- [ ] Autenticación JWT
- [ ] Manejo global de excepciones
- [ ] Migraciones con Flyway
- [ ] Frontend en React
