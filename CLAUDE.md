# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build the project
./mvnw clean package

# Run the application (starts on default port 8080)
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=EcommerceApplicationTests

# Skip tests during build
./mvnw clean package -DskipTests
```

## Architecture

Spring Boot 4.0.1 ecommerce backend API using Java 25, with layered architecture:

- **Controllers** (`controllers/`) — REST endpoints, delegates to services. Exception handling via `@ControllerAdvice` in `handlers/ControllerExceptionHandler`.
- **Services** (`services/`) — Business logic, entity↔DTO conversion, `@Transactional` management. Custom exceptions in `services/exceptions/`.
- **Repositories** (`repositories/`) — Spring Data JPA interfaces extending `JpaRepository`.
- **DTOs** (`dto/`) — API request/response objects with Jakarta Bean Validation (messages in Portuguese).
- **Entities** (`entities/`) — JPA domain model: Product, Category, User, Order, OrderItem (composite key via `OrderItemPK`), Payment, OrderStatus enum.

### Key Entity Relationships

- Product ↔ Category: `@ManyToMany`
- User → Order: `@OneToMany`
- Order → OrderItem: `@OneToMany` with `@EmbeddedId` composite key
- Order ↔ Payment: `@OneToOne` via `@MapsId`

### Database

- Active profile: `test` (H2 in-memory, `jdbc:h2:mem:testdb`)
- H2 console available at `/h2-console` (user: `sa`, no password)
- Seed data loaded from `src/main/resources/import.sql`
- Tables use `tb_` prefix (e.g., `tb_product`, `tb_category`)

### Current API (Product CRUD)

All endpoints under `/products`: GET `/{id}`, GET (paginated), POST, PUT `/{id}`, DELETE `/{id}`.
