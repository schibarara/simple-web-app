# Simple Product Management API

A lightweight Spring Boot REST API for managing products with full CRUD support. The application uses an in-memory H2 database and seeds a couple of sample products at startup.

## Stack

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation
- Maven Wrapper

## Run Locally

Start the app with:

```bash
./mvnw spring-boot:run
```

The API runs at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Service status |
| GET | `/api/products` | Retrieve all products |
| GET | `/api/products/{productId}` | Retrieve a product by ID |
| POST | `/api/products` | Create a product |
| PUT | `/api/products/{productId}` | Update a product |
| DELETE | `/api/products/{productId}` | Delete a product |

## Sample Requests

Create a product:

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "id": 2001,
    "name": "USB Hub",
    "price": 35
  }'
```

Update a product:

```bash
curl -X PUT http://localhost:8080/api/products/1001 \
  -H "Content-Type: application/json" \
  -d '{
    "id": 9999,
    "name": "Mechanical Keyboard",
    "price": 60
  }'
```

The update endpoint uses the `productId` from the URL as the source of truth.

## Seed Data

On startup, the app seeds these products when the database is empty:

- `1001` - `Keyboard` - `45`
- `1002` - `Webcam` - `70`

## Testing

Run the checks locally with:

```bash
./mvnw verify
```

This project includes:

- controller integration tests
- service unit tests
- Checkstyle
- JaCoCo coverage checks
- SpotBugs

## Notes

- The app uses an in-memory H2 database, so data resets when the app restarts.
- The H2 console is not enabled in the current application configuration.

