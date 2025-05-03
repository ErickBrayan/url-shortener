# URL Shortener API

A simple and efficient URL shortening service built with **Spring Boot**, **MySQL**, **Caffeine Cache**, and **Hibernate Validator**.

## Features

- ✅ Create short URLs from long URLs
- 🔍 Retrieve original URLs from short codes
- ✏️ Update existing short URLs
- ❌ Delete short URLs
- 📊 View access statistics for each short URL
- ⚡ In-memory caching with [Caffeine](https://github.com/ben-manes/caffeine)
- 🔒 URL input validation

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Caffeine Cache
- Hibernate Validator

## Getting Started

### Prerequisites

- Java 17+
- MySQL database
- Maven or Gradle

### Configuration

Configure your database connection in `application.yml` or `application.properties`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/url_shortener
    username: your_user
    password: your_password
```

# API Endpoint Testing – URL Shortener

This document contains `curl` examples for testing each endpoint of the URL Shortener API.

---

## 1. Create Short URL

**Endpoint**: `POST /shorten`

**Description**: Create a new short URL.

### Request
```bash
curl -X POST http://localhost:8080/shorten \
  -H "Content-Type: application/json" \
  -d '{"url": "https://www.example.com/some/long/url"}'
```

## 2. Retrieve Original URL

**Endpoint**: `GET /shorten/abc123`

**Description**: Retrieve the original URL from a short URL.

### Request
```bash
curl -X GET http://localhost:8080/shorten/{short-code}
```

## 3. Update Short URL

**Endpoint**: `PATCH /shorten/abc123`

**Description**: Update an existing short URL.

### Request
```bash
curl -X PATCH http://localhost:8080/shorten/{short-code} \
-H "Content-Type: application/json" \
-d '{"url": "https://www.example.com/some/new/long/url"}'
```

## 4. Delete Short URL

**Endpoint**: `DELETE /shorten/abc123`

**Description**: Delete an existing short URL.

### Request
```bash
curl -X DELETE http://localhost:8080/shorten/{short-code}
```

## 5. Get URL Statistics

**Endpoint**: `GET /abc123/stats`

**Description**: Get statistics for a short URL.

### Request
```bash
curl -X GET http://localhost:8080/{short-code}/stats
```


https://roadmap.sh/projects/url-shortening-service