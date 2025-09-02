# 📝 Personal Notes Application - Complete Documentation

## 📋 Table of Contents
- [Project Overview](#project-overview)
- [Core Features](#core-features)
- [Technology Stack](#technology-stack)
- [Quick Start](#quick-start)
- [API Documentation](#api-documentation)
- [Usage Examples](#usage-examples)
- [System Design](#system-design)
- [Security](#security)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Project Overview

Personal Notes Application is a secure note management system built with Spring Boot 3.x. It provides complete user authentication, authorization, and note management capabilities using JWT for stateless authentication, ensuring data security and privacy.

### Use Cases
-  Personal note-taking
-  Work memos and reminders
-  Idea capture
-  Knowledge management
-  Secure information storage

---

## ✨ Core Features

### 1. 🔐 **Authentication & Security**
- **JWT Token Authentication**: Stateless authentication mechanism
- **Password Encryption**: BCrypt algorithm with automatic salt generation
- **Token Expiration**: 24-hour default validity period
- **User Isolation**: Strict data access control per user

### 2. 👤 **User Management**
- **User Registration**: Username, password, and full name
- **User Login**: Returns JWT access token
- **Error Handling**: Meaningful error messages
- **Session Management**: Stateless JWT-based sessions

### 3. 📓 **Note Management**
- **Create Notes**: Support for title and detailed description
- **View Notes**: List view and detailed view
- **Delete Notes**: Secure deletion of personal notes
- **Data Isolation**: Users can only access their own notes
- **Timestamps**: Automatic creation and update timestamps

### 4. 🏗️ **System Features**
- **RESTful API Design**: Standard REST conventions
- **Global Exception Handling**: Unified error response format
- **Input Validation**: Comprehensive request validation
- **H2 In-Memory Database**: Convenient for development
- **Clean Architecture**: Layered design pattern

---

## 🛠️ Technology Stack

### Core Technologies
| Technology | Version | Description |
|------------|---------|-------------|
| **Spring Boot** | 3.1.4 | Core framework |
| **Spring Security** | 6.x | Security framework |
| **Spring Data JPA** | 3.x | ORM framework |
| **JWT** | 0.11.5 | Token authentication |
| **H2 Database** | In-memory | Development database |
| **Maven** | 3.6+ | Build tool |
| **Java** | 17+ | Programming language |
| **Lombok** | Latest | Boilerplate reduction |

### Project Structure
```
src/main/java/com/agridence/microservice/Assignment/
├── 📁 config/           # Configuration classes
│   ├── SecurityConfig.java         # Security configuration
│   └── JwtAuthenticationFilter.java # JWT filter
├── 📁 controller/       # REST controllers
│   ├── AuthController.java         # Authentication endpoints
│   └── NoteController.java         # Note management endpoints
├── 📁 dto/             # Data Transfer Objects
│   ├── SignupRequest.java         # User registration DTO
│   ├── LoginRequest.java          # Login credentials DTO
│   ├── AuthResponse.java          # Authentication response
│   ├── NoteRequest.java           # Note creation/update DTO
│   └── NoteResponse.java          # Note response DTO
├── 📁 entity/          # JPA entities
│   ├── User.java                  # User entity
│   └── Note.java                  # Note entity
├── 📁 exception/       # Exception handling
│   ├── GlobalExceptionHandler.java # Global exception handler
│   └── ResourceNotFoundException.java # 404 exception
├── 📁 repository/      # Data access layer
│   ├── UserRepository.java        # User data access
│   └── NoteRepository.java        # Note data access
├── 📁 service/         # Business logic layer
│   ├── AuthService.java           # Authentication logic
│   ├── NoteService.java           # Note management logic
│   ├── JwtService.java            # JWT operations
│   └── CustomUserDetailsService.java # User details service
└── 📄 AssignmentApplication.java  # Main application class
```

---

## 🚀 Quick Start

### Prerequisites
- ✅ Java 17 or higher
- ✅ Maven 3.6 or higher
- ✅ Git
- ✅ Your favorite IDE (IntelliJ IDEA, VS Code, Eclipse)

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/springboot-assignment.git
cd springboot-assignment
```

### 2️⃣ Configure Application
Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true

# JWT Configuration
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
jwt.expiration=86400000

# Docker Compose (disable if not using Docker)
spring.docker.compose.enabled=false
```

### 3️⃣ Build the Project
```bash
mvn clean install
```

### 4️⃣ Run the Application
```bash
mvn spring-boot:run
```

The application will be available at:
- 🌐 API Endpoints: `http://localhost:8080`
- 💾 H2 Console: `http://localhost:8080/h2-console`

---

## 📖 API Documentation

### Base URL
```
http://localhost:8080
```

### Authentication Endpoints

#### 1. User Registration
```http
POST /api/auth/signup
Content-Type: application/json

Request Body:
{
    "username": "john_doe",
    "password": "SecurePass123",
    "fullName": "John Doe"
}

Response: 201 Created
"User registered successfully"

Error Response: 400 Bad Request
{
    "error": "Username already exists"
}
```

#### 2. User Login
```http
POST /api/auth/login
Content-Type: application/json

Request Body:
{
    "username": "john_doe",
    "password": "SecurePass123"
}

Success Response: 200 OK
{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
    "tokenType": "Bearer"
}

Error Response: 401 Unauthorized
{
    "error": "invalid username/password"
}
```

### Note Endpoints (Authentication Required)

> ⚠️ **Important**: All note endpoints require JWT token in the Authorization header
> ```
> Authorization: Bearer <your-token-here>
> ```

#### 3. Create Note
```http
POST /api/notes
Content-Type: application/json
Authorization: Bearer <token>

Request Body:
{
    "title": "Meeting Notes",
    "description": "Discussed project timeline and deliverables..."
}

Response: 201 Created
{
    "id": 1,
    "title": "Meeting Notes",
    "description": "Discussed project timeline and deliverables...",
    "createdAt": "2025-09-02T10:30:00",
    "updatedAt": "2025-09-02T10:30:00"
}
```

#### 4. Get All Notes
```http
GET /api/notes
Authorization: Bearer <token>

Response: 200 OK
[
    {
        "id": 1,
        "title": "Meeting Notes",
        "description": "Discussed project timeline...",
        "createdAt": "2025-09-02T10:30:00",
        "updatedAt": "2025-09-02T10:30:00"
    },
    {
        "id": 2,
        "title": "Shopping List",
        "description": "Milk, Bread, Eggs",
        "createdAt": "2025-09-02T11:00:00",
        "updatedAt": "2025-09-02T11:00:00"
    }
]
```

#### 5. Get Note by ID
```http
GET /api/notes/{id}
Authorization: Bearer <token>

Response: 200 OK
{
    "id": 1,
    "title": "Meeting Notes",
    "description": "Discussed project timeline...",
    "createdAt": "2025-09-02T10:30:00",
    "updatedAt": "2025-09-02T10:30:00"
}

Error Response: 404 Not Found
{
    "error": "Note not found"
}
```

#### 6. Delete Note
```http
DELETE /api/notes/{id}
Authorization: Bearer <token>

Response: 204 No Content

Error Response: 404 Not Found
{
    "error": "Note not found"
}
```

### Error Response Format

#### Validation Errors
```json
{
    "username": "Username is required",
    "password": "Password is required"
}
```

#### Authentication Errors
```json
{
    "error": "invalid username/password"
}
```

#### Authorization Errors
```json
{
    "error": "Access denied"
}
```

---

## 💻 Usage Examples

### Using cURL

#### Complete Workflow Example
```bash
# 1. Register a new user
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "password": "Alice@123",
    "fullName": "Alice Johnson"
  }'

# 2. Login and save token
TOKEN=$(curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "password": "Alice@123"
  }' | jq -r '.accessToken')

echo "Token: $TOKEN"

# 3. Create your first note
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "title": "My First Note",
    "description": "This is my first note using the Personal Notes App!"
  }'

# 4. Get all notes
curl -X GET http://localhost:8080/api/notes \
  -H "Authorization: Bearer $TOKEN" | jq

# 5. Get specific note
curl -X GET http://localhost:8080/api/notes/1 \
  -H "Authorization: Bearer $TOKEN" | jq

# 6. Delete a note
curl -X DELETE http://localhost:8080/api/notes/1 \
  -H "Authorization: Bearer $TOKEN"
```

### Using Postman

#### Environment Setup
1. Create a new environment: "Notes App Dev"
2. Add variables:
   - `base_url`: `http://localhost:8080`
   - `token`: (will be set automatically after login)

#### Login Script (Tests Tab)
```javascript
// Automatically save token after successful login
if (pm.response.code === 200) {
    const response = pm.response.json();
    pm.environment.set("token", response.accessToken);
    pm.test("Token saved", () => {
        pm.expect(response.accessToken).to.be.a('string');
    });
}
```

#### Collection Setup
```
📁 Personal Notes API
├── 📁 Authentication
│   ├── POST Signup
│   └── POST Login
└── 📁 Notes (Requires Auth)
    ├── POST Create Note
    ├── GET Get All Notes
    ├── GET Get Note by ID
    └── DELETE Delete Note
```

---

## 🏛️ System Design

### Database Schema

#### Entity Relationship Diagram (ERD)
```
┌─────────────────┐         ┌─────────────────┐
│      USER       │         │      NOTE       │
├─────────────────┤         ├─────────────────┤
│ id (PK)         │ 1    *  │ id (PK)         │
│ username (UK)   │◄────────│ user_id (FK)    │
│ password        │         │ title           │
│ full_name       │         │ description     │
│                 │         │ created_at      │
│                 │         │ updated_at      │
└─────────────────┘         └─────────────────┘
```

### Security Architecture

```
Client Request
    ↓
[JWT Authentication Filter]
    ↓
Validate Token → Extract User
    ↓
[Security Context]
    ↓
[Controller Layer]
    ↓
[Service Layer] → Business Logic
    ↓
[Repository Layer] → Data Access
    ↓
[Database]
```

### API Design Principles
- ✅ RESTful conventions
- ✅ Stateless authentication
- ✅ Consistent response format
- ✅ Proper HTTP status codes
- ✅ Clear error messages
- ✅ Input validation
- ✅ Version-ready structure

---

## 🔒 Security

### Implemented Security Measures

#### 1. Password Security
- **BCrypt Encryption**: One-way hashing with salt
- **Salt Generation**: Automatic per-password salt
- **Strength**: 10 rounds (default)

#### 2. JWT Token Security
- **Algorithm**: HMAC-SHA256
- **Expiration**: 24-hour validity
- **Stateless**: No server-side session storage
- **Bearer Token**: Standard Authorization header

#### 3. Access Control
- **User Isolation**: Users can only access their own data
- **Authentication Required**: All note endpoints protected
- **Request-Level Validation**: Each request verified

#### 4. Input Validation
- **DTO Validation**: All inputs validated
- **SQL Injection Prevention**: JPA parameterized queries
- **XSS Protection**: Input sanitization

### Production Security Checklist

⚠️ **Before deploying to production**:

- [ ] Change default JWT secret key
- [ ] Use HTTPS/TLS encryption
- [ ] Configure CORS properly
- [ ] Implement rate limiting
- [ ] Add request logging and monitoring
- [ ] Use production database (PostgreSQL/MySQL)
- [ ] Configure firewall rules
- [ ] Implement refresh tokens
- [ ] Add API versioning
- [ ] Set up backup strategy
- [ ] Configure security headers
- [ ] Implement audit logging

---

## 🔧 Troubleshooting

### Common Issues and Solutions

#### 1. Port Already in Use
**Error**: `Port 8080 is already in use`

**Solution**:
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or change application port
# In application.properties:
server.port=8081
```

#### 2. Docker Not Found
**Error**: `Unable to start docker process`

**Solution**:
Add to `application.properties`:
```properties
spring.docker.compose.enabled=false
```

#### 3. JWT Token Invalid
**Error**: `JWT signature does not match`

**Possible Causes**:
- Token expired
- Wrong token format
- JWT secret mismatch

**Solution**:
- Login again for new token
- Check Authorization header format: `Bearer <token>`
- Verify JWT secret configuration

#### 4. H2 Console Not Accessible
**Solution**:
Ensure these properties are set:
```properties
spring.h2.console.enabled=true
```
Access URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave blank)

#### 5. Maven Build Failures
**Solution**:
```bash
# Clear Maven cache
mvn clean

# Force update dependencies
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

---

##  Performance Optimization

### Database Optimization
- **Indexing**: Add indexes on frequently queried columns
- **Pagination**: Implement pageable endpoints
- **Query Optimization**: Use JPQL for complex queries
- **Connection Pooling**: Configure HikariCP

### API Optimization
```java
// Example: Pagination support
@GetMapping
public Page<NoteResponse> getNotes(
    @PageableDefault(size = 20, sort = "createdAt") Pageable pageable
) {
    return noteService.getUserNotes(authentication, pageable);
}
```

### Caching Strategy
- **Spring Cache**: For frequently accessed data
- **Redis**: For distributed caching
- **HTTP Caching**: ETags and Cache-Control headers

---

## 🚢 Deployment Guide

### Docker Deployment

#### Dockerfile
```dockerfile
FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

#### docker-compose.yml

```yaml
version: '3.8'
services:
  app:
    build: ../../../Desktop
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - JWT_SECRET=${JWT_SECRET}
    volumes:
      - ./logs:/logs
```

#### Build and Run
```bash
# Build Docker image
docker build -t notes-app:1.0 .

# Run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f
```

### Cloud Deployment Options

#### AWS Elastic Beanstalk
```bash
# Install EB CLI
pip install awsebcli

# Initialize EB
eb init -p java-17 notes-app

# Create environment and deploy
eb create production
eb deploy
```

#### Heroku
```bash
# Create Heroku app
heroku create notes-app

# Deploy
git push heroku main

# View logs
heroku logs --tail
```

---

## 📈 Monitoring and Metrics

### Spring Boot Actuator
Add to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Configure endpoints:
```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

### Available Metrics
- `/actuator/health` - Application health
- `/actuator/metrics` - Application metrics
- `/actuator/info` - Application information

---

## 🧪 Testing

### Unit Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=NoteServiceTest

# Generate test coverage report
mvn jacoco:report
```
