# Softlend Credit System

## Overview

A Spring Boot backend application that helps customers improve their credit profile and discover loan offers based on their CIBIL score.

Features:

* Customer Management
* Credit Gap Tracking
* Credit Profile Analysis
* Loan Offer Management
* Offer Score-Gating
* EMI Calculation
* Flyway Database Migrations
* Global Exception Handling

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JPA
* MySQL
* Flyway
* Maven
* Lombok

---

## Database Setup

Create database:

```sql
CREATE DATABASE credit_system;
```

Update application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/credit_system
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## Flyway Migrations

Migration files:

* V1__create_customer_table.sql
* V2__create_credit_gap_table.sql
* V3__create_offers_table.sql

Flyway automatically runs migrations during application startup.

---

## Run Application

```bash
mvn spring-boot:run
```

Application starts at:

```text
http://localhost:8081
```

---

## API Endpoints

### Customers

POST /customers

GET /customers

GET /customers/{id}

GET /customers/{id}/credit-profile

GET /customers/{id}/improvement-summary

---

### Credit Gaps

POST /customers/{id}/credit-gaps

PATCH /credit-gaps/{id}/resolve

---

### Offers

POST /customers/{id}/offers

GET /customers/{id}/offers

PATCH /customers/offers/{id}/status

GET /customers/offers/{id}/emi

---

## Error Response Format

```json
{
  "error": "Customer not found",
  "code": "CUSTOMER_NOT_FOUND"
}
```

---

## Postman Collection

Import the provided Postman collection and execute the APIs in the following order:

1. Create Customer
2. Create Credit Gap
3. Resolve Credit Gap
4. Create Offer
5. Get Offers
6. Update Offer Status
7. Calculate EMI

---

## Bonus Features

* Improvement Summary API
* Offer Score Gating
* EMI Calculator
* Global Exception Handling
