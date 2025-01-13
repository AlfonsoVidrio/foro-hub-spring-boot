# Foro Hub

Foro Hub is a REST API built with Spring Boot for managing forum topics and replies. It includes JWT-based user authentication, allowing users to create, update, delete, and view topics, as well as post replies. Access is secured through JWT tokens, ensuring that only authorized users can interact with the API.

## Table of Contents
1. [Technologies Used](#technologies-used)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Environment Variables Setup](#environment-variables-setup)
    - [Required Variables](#required-variables)
    - [Configuration Example](#configuration-example)
5. [Database Setup](#database-setup)
    - [Create the Database](#create-the-database)
    - [Insert User into Database](#insert-user-into-database)
    - [Insert Courses into Database](#insert-courses-into-database)
6. [Documentation](#documentation)
7. [Author](#author)
8. [Badge](#badge)

## Technologies Used
- **Flyway**: Database versioning.
- **Java 17**: Programming language.
- **Lombok**: Reduces repetitive code.
- **MySQL**: Database for storing data.
- **Spring Boot**: Framework for building the REST API.
- **Spring Boot Security**: Security management and JWT authentication.
- **Spring Validation**: Input and data validation.
- **Springdoc**: API documentation generation with Swagger.

## Features
- Create topics.
- Update topics.
- Delete topics.
- Get topics by ID.
- List all topics.

## Requirements
- **Java 17** or higher.
- **MySQL** as the database system.
- **Maven** as the dependency manager.

## Environment Variables Setup

For the application to work correctly, you need to configure the following environment variables in your operating system. These variables are used to establish the connection to the MySQL database and define certain important parameters for the application's operation.

### Required Variables
- **`DB_HOST`**: The address or hostname of the MySQL database (e.g., localhost or an IP address).
- **`DB_NAME`**: The name of the MySQL database to be used (e.g., foro_hub).
- **`DB_USERNAME`**: The username with permissions to access the database.
- **`DB_PASSWORD`**: The password associated with the database user.
- **`JWT_SECRET`**: The secret used for signing the JWT.

### Configuration Example

You should add the following environment variables to your system, replacing the values as per your configuration:

```properties
spring.application.name=forohub

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.secret=${JWT_SECRET}
```
Ensure that these variables are properly configured before running the application.

## Database Setup
Before running the application, create the MySQL database named `forohub` and set up the necessary tables. You should also create a user for the application with the following credentials:

- **Username**: `user.test`
- **Email**: `user18@test.com`
- **Password**: `$2a$10$Q8MLbVpiHs9lw0iL2nI8oeITZDZJSai0lwfAtDfMMI`

### Create the Database
<div>
  <img src="https://github.com/user-attachments/assets/254a8ece-ea9c-44a3-9854-2bc8b3d32654" alt="documentation swagger" style="max-width: 550px;">
</div>

Run the following SQL command to create the database:

```sql
CREATE DATABASE forohub;
```

After creating the database, they need to run the project. Flyway, which is integrated into the project, will automatically create the necessary tables when the project is run for the first time.

### Insert User into Database
Run the following SQL command to insert the user into the database:

```sql
INSERT INTO users (name, email, password) VALUES ('user.test', 'user18@test.com', '$2a$10$Q8MLbVpiHs9lw0iL2nI8oeITZDZJSai0lwfAtDfMMI');
```

### Insert Courses into Database
Run the following SQL command to insert three courses into the courses table:
```sql
INSERT INTO courses (name, description, category) 
VALUES 
  ('Java Programming 101', 'Learn the basics of Java programming including syntax, data types, and control structures.', 'PROGRAMMING'),
  ('Introduction to Graphic Design', 'An introductory course to graphic design, covering design principles and software usage.', 'DESIGN'),
  ('Marketing Strategies for Beginners', 'Explore the fundamentals of marketing, from market research to social media strategies.', 'MARKETING');
```
## Documentation
You can view the Swagger documentation for the API by navigating to the following URL after running the application:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

<div>
  <img src="https://github.com/user-attachments/assets/f51bcb4e-76dc-4466-a743-6744f5ad633c" alt="documentation swagger" style="max-width: 550px;">
</div>


## Author
Developed by Alfonso Manuel Vidrio Lizaola.

[LinkedIn: Alfonso Manuel Vidrio Lizaola](https://www.linkedin.com/in/alfonsomanuelvidriolizaola/)

## Badge
<div>
<img src="https://github.com/user-attachments/assets/e1356114-5711-4ee8-8239-0eb3dbf15a36" alt="badge spring boot" style="max-width: 400px; height: auto;">
</div>


