# 🎓 Student Management REST API

A Spring Boot RESTful API for managing student data with validation, exception handling, and clean API design.

---

## 🚀 Features

- Create, Read, Update, Delete (CRUD) students
- Input validation using Bean Validation
- Global exception handling
- Partial update support
- Duplicate email check
- Calculated age from date of birth
- Clean and consistent API error responses

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Hibernate Validator
- Postman (for API testing)

---

## 📦 API Endpoints

| Method | Endpoint | Description |
|------|---------|-------------|
| POST | `/api/v1/student` | Create a student |
| GET | `/api/v1/student` | Get all students |
| GET | `/api/v1/student/{id}` | Get student by ID |
| PUT | `/api/v1/student/{id}` | Update student |
| DELETE | `/api/v1/student/{id}` | Delete student |

---

## ❌ Validation Example

### Request (Invalid Input)

```json
{
  "name": "",
  "email": "abc",
  "dob": "2002-01-01"
}

{
  "status": 400,
  "error": "Validation Failed",
  "messages": {
    "name": "Name is mandatory",
    "email": "Email should be valid"
  }
}

---

## 📄 License

This project is licensed under the [MIT License](LICENSE) © 2026 Sam Kumar.
