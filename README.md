# HealthX API – Therapy Session Management System

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

REST API developed in Java with Spring Boot for managing psychologists,
patients, and therapy sessions. The project was built with a focus on security,
code organization, and software engineering best practices, and is designed
to be integrated with frontend applications.
## ✨ Features

### 👨‍⚕️ Psychologist Management
- Register psychologist profiles with professional information
- Update psychologist data
- View all registered psychologists
- Delete psychologist profiles

### 👤 Patient Management
- Create and manage patient records
- Store patient information securely
- Update patient data
- View patient history
- Remove patient records

### 📅 Session Scheduling & Management
- **Create therapy sessions** between psychologists and patients
- **Automatic schedule conflict detection** - prevents double-booking
- Update session details (date, time, status)
- View all scheduled sessions
- Cancel or reschedule sessions

### 🔐 Authentication & Authorization
- **User registration** as a psychologist
- **Secure login** with JWT token generation
- **Role-based access control** - different permissions for psychologists
- **Protected endpoints** - only authenticated users can access sensitive data
- Token-based session management

### 🛡️ Additional Features
- **Input validation** - ensures data integrity
- **Error handling** - clear and informative error messages
- **Database persistence** with PostgreSQL
- **RESTful API design** - follows REST best practices
- **API documentation** with Swagger/OpenAPI

---

## 🎯 Use Cases

1. **Psychologist registers** in the system
2. **Psychologist logs in** and receives a JWT token
3. **Psychologist creates patient records**
4. **Psychologist schedules therapy sessions**
5. **System validates** that the psychologist is available at the requested time
6. **System prevents** scheduling conflicts automatically
7. **Psychologist manages** their sessions and patients



## 🚀 Technologies Used

### Backend
- Java 17
- Spring Boot
- Spring Web (REST APIs)
- Spring Data JPA
- Hibernate

### Security
- Spring Security
- Authentication and authorization with protected endpoints

### Database
- PostgreSQL

### Documentation
- Swagger / OpenAPI

### Build & Tools
- Maven
- Docker

## 🚀 How to Run the Project

### Prerequisites

- Java 17+
- Maven
- PostgreSQL
- An IDE or terminal
- Docker
- Docker Compose

1️⃣ Clone the repository

```
git clone https://github.com/luisvso/HealthXAPI.git
```
```
cd HealthXAPI
```
## 
After cloning the repository, the application must be configured using environment variables.

2️⃣ Configure environment variables

A `.env_template` file is provided in the root of the project. Rename it to `.env`:
```bash
mv .env_template .env
```
Then, open the `.env` file and fill in the required values:
```env
# Database Configuration
POSTGRES_USER=healthx_user
POSTGRES_DB=healthx
POSTGRES_PASSWORD=your_secure_password

# PgAdmin Configuration (optional - for database management)
PGADMIN_DEFAULT_EMAIL=admin@healthx.com
PGADMIN_DEFAULT_PASSWORD=your_pgadmin_password

# JWT Configuration
JWT_SECRET=your_jwt_secret_key_minimum_256_bits_recommended
```

**Important Notes:**
- `JWT_SECRET`: Must be a secure random string (recommended minimum 256 bits / 32 characters)
- `PGADMIN_DEFAULT_EMAIL`: Email to access PgAdmin interface
- `PGADMIN_DEFAULT_PASSWORD`: Password to access PgAdmin interface
- Keep these values secret and never commit the `.env` file to version control

## 🔒 Security Notes

- Never commit your `.env` file to the repository
- The `.env` file is already included in `.gitignore`
- Use strong passwords for production environments
- Generate a secure JWT_SECRET using:
```bash
  openssl rand -base64 32
```
- For production, consider using environment-specific secrets management

### 3️⃣ Start the database

Start PostgreSQL and PgAdmin with Docker Compose:
```bash
docker-compose up -d
```

**Verify the containers are running:**
```bash
docker-compose ps
```

You should see:
- `healthx-db` - PostgreSQL (port 5432)
- `healthx-pgadmin` - PgAdmin (port 5050)

### 4️⃣ Run the Spring Boot application

####  Using Terminal

```bash
mvn spring-boot:run
```

*Using an IDE (IntelliJ, Eclipse, VS Code):**

Simply run the main application class (the one annotated with `@SpringBootApplication`).

---

### 5️⃣ Verify the application is running

Once the application starts, you should see logs ending with:
```
Started HealthXApplication in X.XXX seconds
```

**Access the application:**
- **API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
<img width="1894" height="1017" alt="Screenshot From 2026-02-01 16-33-25" src="https://github.com/user-attachments/assets/4b64e339-65f2-4321-a6a7-30be6cd214ca" />

There you can:
- View all available endpoints
- Test the API directly in the browser
- See request/response schemas
- Understand authentication requirements



