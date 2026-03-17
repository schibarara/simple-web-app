# 🛒 Simple Product Management API – Spring Boot

A lightweight **REST API** built with **Spring Boot** for managing products. It supports **CRUD operations** (Create, Read, Update, Delete) and uses an in-memory **H2 database**.

> 🌐 Runs locally at: `http://localhost:8080`

---

## 📦 Features

- ➕ Add new products  
- 📄 Retrieve all products  
- 🔍 Retrieve a product by ID  
- 📝 Update product details  
- ❌ Delete a product  

---

## 🧩 Technologies Used

| Technology      | Description                       |
|----------------|-----------------------------------|
| Java 17+       | Programming Language              |
| Spring Boot    | Main framework for building APIs  |
| Spring Web     | REST API handling                 |
| H2 Database    | In-memory database for persistence|
| Postman        | API testing tool                  |

---

## 📁 API Endpoints

| Method | Endpoint                         | Description               |
|--------|----------------------------------|---------------------------|
| GET    | `/get products`                  | Retrieve all products     |
| GET    | `/get products/{prodid}`         | Retrieve product by ID    |
| POST   | `/get products`                  | Add a new product         |
| PUT    | `/update products`               | Update existing product   |
| DELETE | `/get products/delete/{prodid}`  | Delete product by ID      |

---

## 🚀 How to Run the Project

### Step 1: Run the Application

#### Option 1: Using Maven Wrapper

```bash
./mvnw spring-boot:run
```

#### Option 2: Run from IDE

- Open `SimpleWebAppApplication.java`
- Run the `main()` method

### Step 2: Access the Application

- **API Base URL:** `http://localhost:8080`  
- **H2 Console:** `http://localhost:8080/h2-console`

---

## 🧪 Testing the API with Postman

1. Open **Postman**.
2. Choose the appropriate HTTP method (GET, POST, PUT, DELETE).
3. Use an endpoint like:  
   `http://localhost:8080/get products`

### Sample JSON for POST / PUT

```json
{
  "prodid": 101,
  "prodname": "Wireless Mouse",
  "price": 499.99
}
```

### Required Headers

| Key           | Value              |
|----------------|--------------------|
| Content-Type   | application/json   |

---

## 🗃️ H2 Database Console

The app uses an **in-memory H2 database**. Data resets every time the server restarts.

### 🔗 Access Console

- URL: `http://localhost:8080/h2-console`

### 🔐 Default Credentials

| Property        | Value                  |
|----------------|------------------------|
| JDBC URL       | `jdbc:h2:mem:testdb`   |
| Username       | `sa`                   |
| Password       | *(leave blank)*        |

### ✅ Required Configuration (in `application.properties`)

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
```

---


