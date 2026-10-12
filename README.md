# Akluj City Project — Backend

## About the Project

The Akluj City Project backend provides server-side functionality and REST APIs for the Akluj City web application.

It handles application requests, business logic, data management, and communication between the Angular frontend and the database.

## Features

- RESTful API development
- User registration and authentication, where implemented
- User and administrative operations
- City-related information management
- Database integration
- Request validation and error handling
- JWT-based authentication and authorization, if configured
- Role-based access control, if configured

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA,
- Spring Security,
- MySQL,
- Maven
- REST APIs

## Prerequisites

Install the following before running the backend:

- Java JDK compatible with the project
- Maven, if not using the included Maven Wrapper
- MySQL, if required by the application
- Git
- IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/97Aditya/akluj-city-backend.git
```

### 2. Navigate to the Project

```bash
cd <BACKEND_PROJECT_FOLDER>
```

### 3. Configure the Database

If the application uses MySQL, create the required database.

For example:

```sql
CREATE DATABASE akluj_city;
```

Update the database URL, username, and password in:

`src/main/resources/application.properties`

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/akluj_city
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

Configure the corresponding environment variables before starting the application. Use the actual database name and settings expected by your project.

**Security note:** Never commit database passwords, JWT secrets, or other credentials to a public repository.

### 4. Install Dependencies and Run

Using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

Alternatively, if the repository includes the Maven Wrapper:

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

### 5. Verify the Application

The backend commonly starts at:

http://localhost:8080/

The root URL may not return a webpage. Verify the application using its actual API endpoints or health-check endpoint, if configured.

## API Integration

The backend exposes REST endpoints consumed by the Angular frontend.

Examples of common endpoint categories include:

- Authentication and registration
- User management
- City information
- Education and institution data
- Restaurant information
- Tourist attractions

The actual endpoints, HTTP methods, request formats, and responses depend on the implemented controllers.

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── <your-package>/
    │       ├── controller/
    │       ├── service/
    │       ├── repository/
    │       ├── entity/
    │       ├── dto/
    │       └── config/
    └── resources/
        └── application.properties
```

This is a typical Spring Boot structure. Keep only the directories that exist in your actual project.

## Configuration Notes

- Verify the Java version specified by the project.
- Ensure MySQL is running if the backend uses it.
- Configure the database credentials and connection URL.
- Configure CORS to allow requests from the frontend origin.
- Ensure authentication and authorization settings match the frontend.
- Never commit private keys, passwords, or production credentials.

## Author

**Aditya Burade**

Software Developer | Java | Spring Boot | REST APIs | MySQL

## License

This project is intended for learning and portfolio purposes. Add a license if you intend to distribute it under specific reuse terms.
