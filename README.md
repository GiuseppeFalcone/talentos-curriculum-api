# Curriculum API

A RESTful API built with Spring Boot for managing curriculum vitae (CV) data. This API provides comprehensive endpoints for creating, reading, updating, and deleting curriculum information including education history, projects, and personal details.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database Setup](#database-setup)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [License](#license)

## ✨ Features

- **CRUD Operations**: Complete Create, Read, Update, and Delete operations for curriculum data
- **Pagination Support**: Efficient pagination for listing curriculums
- **Advanced Filtering**: Filter curriculums by user IDs and domain options
- **Education History**: Manage educational background information
- **Projects Management**: Track professional projects and experiences
- **Validation**: Input validation using Jakarta Bean Validation
- **API Documentation**: Interactive API documentation with Swagger/OpenAPI
- **JWT Integration**: JWT claims extraction for secure authentication
- **Multiple Environments**: Support for development, testing, and production profiles

## 🛠 Technology Stack

- **Java**: 25
- **Spring Boot**: 3.5.7
- **Spring Data JPA**: For database operations
- **MySQL**: Database
- **Lombok**: Reduce boilerplate code
- **MapStruct**: Object mapping
- **SpringDoc OpenAPI**: API documentation (Swagger UI)
- **Maven**: Build and dependency management

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- Java 25 or higher
- Maven 3.6+
- MySQL 8.0+
- Git (for cloning the repository)

## 🚀 Installation

1. Clone the repository:
```bash
git clone https://github.com/GiuseppeFalcone/talentos-curriculum-api.git
cd talentos-curriculum-api
```

2. Build the project:
```bash
./mvnw clean install
```

## ⚙️ Configuration

The application supports multiple profiles (dev, test, prod). Configuration is managed through environment variables.

### Required Environment Variables

- `SPRING_PROFILES_ACTIVE`: Profile to activate (dev, test, or prod)
- `PORT`: Port number for the application server
- `SQL_DB_URL_DEV`: Database URL for development
- `SQL_DB_USER`: Database username
- `SQL_DB_PSW`: Database password

### Development Profile Example

Create a `.env` file in the project root (not committed to version control):

```properties
SPRING_PROFILES_ACTIVE=dev
PORT=8080
SQL_DB_URL_DEV=jdbc:mysql://localhost:3306/curriculum_db
SQL_DB_USER=your_username
SQL_DB_PSW=your_password
```

### Application Properties

The application is configured with:
- JPA/Hibernate with MySQL dialect
- API documentation at `/curriculum-api/docs`
- Swagger UI at `/curriculum-api/swagger-ui.html`
- Request/response logging enabled

## 🏃 Running the Application

### Using Maven

```bash
./mvnw spring-boot:run
```

### Using Java

```bash
java -jar target/curriculumapi-0.0.1-SNAPSHOT.jar
```

The application will start on the configured port (default: 8080).

## 📖 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/curriculum-api/swagger-ui.html
- **OpenAPI Docs**: http://localhost:8080/curriculum-api/docs

### Main Endpoints

#### Get All Curriculums
```
GET /api/curriculums?page=1&pageSize=5
```
Optional query parameters:
- `page`: Page number (default: 1, must be > 0)
- `pageSize`: Items per page (default: 5, must be > 0)
- `userIds`: Filter by user IDs (comma-separated)
- `domainOptionIds`: Filter by domain option IDs (comma-separated)

#### Get Curriculum by ID
```
GET /api/curriculums/{curriculumId}
```

#### Create Curriculum
```
POST /api/curriculums
Content-Type: application/json

{
  "userId": 101,
  "mobilePhone": "+39 123 456 7890",
  "homeAddress": "123 Main St, City, Country",
  "workAddress": "456 Office St, City, Country",
  "maritalStatus": false,
  "hasCar": true,
  "openForTravel": true,
  "summary": "Professional summary...",
  "drivingLicense": "B",
  "educationHistory": [...],
  "projects": [...],
  "domainOptions": [...]
}
```

#### Update Curriculum
```
PUT /api/curriculums/{curriculumId}
Content-Type: application/json
```

#### Delete Curriculum
```
DELETE /api/curriculums/{curriculumId}
```

### Postman Collection

A Postman collection is included in the repository (`curriculum-api-postman-collection.json`) with pre-configured requests and tests for all API endpoints.

## 🗄️ Database Setup

### Using the Provided SQL Dump

A database schema dump is provided in `dump-easy-cv-dev-curriculumapi-202512181249.sql`.

1. Create the database:
```bash
mysql -u root -p -e "CREATE DATABASE curriculum_db;"
```

2. Import the schema:
```bash
mysql -u root -p curriculum_db < dump-easy-cv-dev-curriculumapi-202512181249.sql
```

### Database Schema

The main tables include:
- `curriculum`: Core curriculum information
- `education`: Education history entries
- `project`: Professional projects
- `project_domain_option`: Project domain classifications

## 🧪 Testing

Run the test suite:

```bash
./mvnw test
```

The project uses Spring Boot Test for integration testing.

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/certimetergroup/talentos/curriculumapi/
│   │       ├── config/          # Configuration classes
│   │       ├── controller/      # REST controllers
│   │       ├── context/         # Request context management
│   │       ├── filter/          # JWT and request filters
│   │       ├── mapper/          # MapStruct mappers
│   │       ├── model/           # JPA entities
│   │       └── service/         # Business logic
│   └── resources/
│       ├── application.properties         # Main configuration
│       ├── application-dev.properties     # Development profile
│       ├── application-test.properties    # Test profile
│       └── application-prod.properties    # Production profile
└── test/                        # Test classes
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Giuseppe Falcone**

---

For more information or questions, please open an issue in the repository.