# Curriculum API

> A RESTful microservice for curriculum vitae and professional profile management in the TalentOS ecosystem

## Overview

**Curriculum API** is a Spring Boot-based microservice that manages user curriculum data, including profile summary, education history, projects, and domain-option classifications.

It exposes a clean API to create, retrieve, replace, delete, and search curriculums with pagination and filtering support. The service integrates with other TalentOS services through shared DTOs and a standardized response envelope from the common TalentOS library.

## Key Features

- **Curriculum CRUD**: Create, retrieve, update, and delete curriculum records
- **Nested Data Management**: Persist and update education history, projects, and project domain options
- **Advanced Search & Filtering**: Filter curriculum listings by user IDs and domain option IDs with pagination
- **User-Scoped Creation**: Enforce one curriculum per authenticated user context
- **Transactional Replace Logic**: Full curriculum replacement with relation re-linking and orphan removal
- **Data Mapping**: MapStruct-based entity/DTO mapping for clean contracts
- **Input Validation**: Jakarta Bean Validation for path/query/body safety
- **Global Error Handling**: Centralized exception mapping with standardized service responses
- **JWT Claim Extraction**: Request filter extracts user identity from bearer token claims
- **API Documentation**: Interactive Swagger UI and OpenAPI 3 documentation
- **Multi-Environment Configuration**: Dedicated dev/test/prod profile properties

## Technology Stack

| Component                 | Technology                  | Version |
| ------------------------- | --------------------------- | ------- |
| **Framework**             | Spring Boot                 | 3.5.7   |
| **Language**              | Java                        | 25      |
| **Database**              | MySQL                       | 8.0+    |
| **ORM**                   | Spring Data JPA / Hibernate | 3.5.7   |
| **Validation**            | Jakarta Bean Validation     | 3.5.7   |
| **Mapping**               | MapStruct                   | 1.6.3   |
| **Boilerplate Reduction** | Lombok                      | 1.18.42 |
| **API Documentation**     | SpringDoc OpenAPI           | 2.8.13  |
| **Build Tool**            | Maven                       | 4.0.0   |

## Project Structure

```text
src/
├── main/
│   ├── java/com/certimetergroup/talentos/curriculumapi/
│   │   ├── CurriculumApiApplication.java          # Spring Boot entry point
│   │   ├── config/
│   │   │   ├── FilterConfig.java                  # Servlet filter registration
│   │   │   └── SwaggerConfig.java                 # OpenAPI/Swagger configuration
│   │   ├── context/
│   │   │   └── RequestContext.java                # Request-scoped user context
│   │   ├── controller/
│   │   │   ├── CurriculumController.java          # Curriculum endpoints
│   │   │   └── ExceptionController.java           # Global exception handling
│   │   ├── filter/
│   │   │   └── JwtClaimExtractor.java             # Authorization token claim extraction
│   │   ├── service/
│   │   │   └── CurriculumService.java             # Curriculum business logic
│   │   ├── repository/
│   │   │   ├── CurriculumRepository.java          # Curriculum JPA repository
│   │   │   └── specification/
│   │   │       └── CurriculumSpecification.java   # Dynamic query specifications
│   │   ├── mapper/
│   │   │   ├── CurriculumMapper.java              # Curriculum entity <-> DTO mapping
│   │   │   ├── EducationMapper.java               # Education mapping
│   │   │   ├── ProjectMapper.java                 # Project mapping
│   │   │   └── ProjectDomainOptionMapper.java     # Domain option mapping
│   │   └── model/
│   │       ├── Curriculum.java                    # Curriculum entity
│   │       ├── Education.java                     # Education entity
│   │       ├── Project.java                       # Project entity
│   │       └── ProjectDomainOption.java           # Domain option relation entity
│   └── resources/
│       ├── application.properties                 # Shared/base properties
│       ├── application-dev.properties             # Development profile
│       ├── application-prod.properties            # Production profile
│       └── application-test.properties            # Test profile
└── test/
   └── java/com/certimetergroup/talentos/curriculumapi/
      └── CurriculumApiApplicationTests.java     # Spring context test
```

## Getting Started

### Prerequisites

- **Java 25** or higher
- **Maven 3.6+** (or Maven Wrapper)
- **MySQL 8.0+** (configured separately)
- Access to the internal artifact repository for `com.certimetergroup.talentos:commons`

### Installation

1. **Clone the repository**

```bash
git clone <repository-url>
cd curriculum-api
```

2. **Set up environment variables**

```bash
export SPRING_PROFILES_ACTIVE=dev
export PORT=8083
export SQL_DB_URL_DEV="jdbc:mysql://localhost:3306/easy_cv_dev"
export SQL_DB_USER="root"
export SQL_DB_PSW="password"
```

3. **Build the project**

```bash
./mvnw clean package
```

4. **Run the application**

```bash
./mvnw spring-boot:run
```

Or run the built JAR:

```bash
java -jar target/curriculumapi-0.0.1-SNAPSHOT.jar
```

The service starts on the configured `PORT` (for example, `8083`).

## API Usage

### Base URL

```text
http://localhost:8083/api/curriculums
```

### Response Contract

All endpoints return a standardized envelope:

- `responseEnum`: service outcome/status code semantic
- `payload`: endpoint-specific data (or null)

### Authentication Context

Requests under `/api/*` pass through a filter that reads the bearer token from `Authorization`, decodes JWT payload claims, and stores `sub` as the request user ID.

This user context is used during curriculum creation to enforce one curriculum per user.

### Example Endpoints

**Get All Curriculums (with pagination and filtering)**

```http
GET /api/curriculums?page=1&pageSize=5&userIds=101,102&domainOptionIds=18,19
```

**Get Curriculum by ID**

```http
GET /api/curriculums/{curriculumId}
```

**Create Curriculum**

```http
POST /api/curriculums
```

**Replace Curriculum Data**

```http
PUT /api/curriculums/{curriculumId}
```

**Delete Curriculum**

```http
DELETE /api/curriculums/{curriculumId}
```

### API Documentation

Interactive API documentation is available at:

- **Swagger UI**: `http://localhost:8083/curriculum-api/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8083/curriculum-api/docs`

## Configuration

### Environment Profiles

**Development** (`application-dev.properties`):

- Development database URL via `SQL_DB_URL_DEV`
- Local SQL logging enabled (`spring.jpa.show-sql=true`)
- Development schema strategy set to `update`

**Production** (`application-prod.properties`):

- Production database URL via `SQL_DB_URL_PROD`
- Environment-based credentials and settings

**Test** (`application-test.properties`):

- Test database URL via `SQL_DB_URL_TEST`
- Test schema strategy set to `update`

### Key Configuration Properties

```properties
# Application
spring.application.name=curriculum-api
server.port=${PORT}
spring.profiles.active=${SPRING_PROFILES_ACTIVE}

# Database
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Externalized datasource properties (profile-specific files)
# application-dev.properties  -> ${SQL_DB_URL_DEV}
# application-test.properties -> ${SQL_DB_URL_TEST}
# application-prod.properties -> ${SQL_DB_URL_PROD}
spring.datasource.username=${SQL_DB_USER}
spring.datasource.password=${SQL_DB_PSW}

# Swagger/API Documentation
springdoc.api-docs.path=/curriculum-api/docs
springdoc.swagger-ui.path=/curriculum-api/swagger-ui.html
```

## Data Model

### Curriculum Entity

- **id**: Auto-generated primary key
- **userId**: Owner user identifier
- **summary**: Profile summary text
- **educationHistory**: Set of associated `Education` records
- **projects**: Set of associated `Project` records
- **domainOptions**: Set of curriculum-level `ProjectDomainOption` records

### Education Entity

- **id**: Auto-generated primary key
- **schoolNameId**: Reference ID for school/domain value
- **degreeNameId**: Reference ID for degree/domain value
- **grade / maxGrade**: Academic scoring values
- **startDate / endDate**: Education timeline

### Project Entity

- **id**: Auto-generated primary key
- **startDate / endDate**: Project timeline
- **description**: Required project description (TEXT)
- **domainOptions**: Set of associated `ProjectDomainOption` records

### ProjectDomainOption Entity

- **id**: Auto-generated primary key
- **domainId**: Parent domain reference
- **domainOptionId**: Selected domain-option reference
- **grade**: Skill level score (0..5)
- **project / curriculum**: Relation ownership fields

## Error Handling

Global exception handling is provided via `ExceptionController` (`@RestControllerAdvice`).

Handled categories include:

- Validation errors (`ConstraintViolationException`, `MethodArgumentNotValidException`, `BindException`)
- Business exceptions (`FailureException`)
- Database/JDBC errors (`JDBCException`, `JDBCConnectionException`)
- Generic fallback (`Exception`)

Validation failures return `BAD_REQUEST` with a payload map of `field/path -> message`.

## Testing

Run unit/integration tests:

```bash
./mvnw test
```

The repository includes a Postman collection:

- `curriculum-api-postman-collection.json`

It provides:

- endpoint requests
- JSON schema validations
- workflow scenario (create -> update -> delete)

## Project Statistics

- **Language**: Java 25
- **Framework**: Spring Boot 3.5.7
- **Build System**: Maven
- **Database**: MySQL with Hibernate/JPA
- **API Documentation**: OpenAPI 3.0 / Swagger UI

## Dependencies Management

Dependencies are managed via Maven in `pom.xml`.

Core dependencies include:

- Spring Boot starters: Web, Data JPA, Validation, Test
- MySQL Connector/J
- MapStruct + annotation processor
- Lombok + Lombok-MapStruct binding
- SpringDoc OpenAPI
- Shared TalentOS commons module

Check for dependency updates with:

```bash
./mvnw dependency:resolve
./mvnw versions:display-dependency-updates
```

## Development Guidelines

### Code Structure

- **Controllers**: Handle HTTP requests and standardized response contracts
- **Services**: Implement business rules and transactional orchestration
- **Repositories**: Perform persistence operations and specification-based filtering
- **Mappers**: Convert entities and shared DTOs through MapStruct
- **Models**: Keep persistence concerns isolated from API payload contracts
- **Filters/Context**: Extract and propagate request-scoped user identity

### Best Practices

- Keep business logic in services, not controllers
- Validate request parameters and payloads at the boundary
- Use specifications for searchable, composable query filters
- Preserve DTO-based contracts to avoid exposing entity internals
- Maintain profile-based environment isolation for deployment safety

## Contributing

When contributing to this project:

1. Follow the existing package structure and naming conventions
2. Add validation and error handling for new request paths
3. Keep mapper and relation-sync logic deterministic and testable
4. Update this README when introducing new endpoints or config changes

## License

MIT License. See [LICENSE](LICENSE) for details.

---

**Developed by Giuseppe Falcone**
