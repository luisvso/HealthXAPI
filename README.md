# HealthX API – Therapy Session Management System


REST API developed in Java with Spring Boot for managing psychologists,
patients, and therapy sessions. The project was built with a focus on security,
code organization, and software engineering best practices, and is designed
to be integrated with frontend applications.

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


