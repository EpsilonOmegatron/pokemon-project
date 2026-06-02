# Pokémon Project

A Spring Boot REST API for managing Pokémon data, including abilities, moves, and user favorites. This project is modeled after the PokémonDB website.

## Table of Contents

- [Overview](#overview)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Features](#features)

## Overview

This API provides comprehensive Pokémon information management with user authentication and personalization features. Users can:
- Browse Pokémon data including types, abilities, and moves
- Manage moves and abilities for Pokémon
- Register accounts and save favorite Pokémon
- Retrieve detailed information about moves and abilities

The project uses JWT-based authentication

## Technologies

- **Java 17** - Programming language
- **Spring Boot 4.0.6** - Framework
- **Spring Data JPA** - ORM and database access
- **Spring Security** - Authentication and authorization
- **Spring Web MVC** - REST API development
- **JWT (JSON Web Tokens)** - Authentication mechanism
- **MySQL** - Database
- **Lombok** - Boilerplate code reduction
- **MapStruct** - Object mapping/DTO conversion
- **SpringDoc OpenAPI** - API documentation
- **Maven** - Build and dependency management

## Prerequisites

- **Java 17 or higher** - [Download](https://adoptium.net/)
- **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- **MySQL 8.0+** - [Download](https://www.mysql.com/downloads/)

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/EpsilonOmegatron/pokemon-project.git
   cd pokemon-project
   ```

2. **Create a `.env` file** in the project root directory with the following environment variables:
   ```properties
   DB_URL=jdbc:mysql://localhost:3306/pokemon_db
   DB_USERNAME=your_db_username
   DB_PASSWORD=your_db_password
   JWT_SECRET=your-secret-key-for-jwt-token-generation
   JWT_EXPIRY=86400000
   ```

   **Configuration Details:**
   - `DB_URL` - MySQL database connection URL
   - `DB_USERNAME` - Database username
   - `DB_PASSWORD` - Database password
   - `JWT_SECRET` - Secret key for signing JWT tokens. Must be base64 encoded and at least 256 bits (32 bytes).
   - `JWT_EXPIRY` - JWT token expiration time in milliseconds

3. **Create the database** (if not auto-created by Hibernate):
   ```bash
   mysql -u root -p
   CREATE DATABASE pokemon_db;
   EXIT;
   ```

4. **Install dependencies**
   ```bash
   mvn clean install
   ```

## Configuration

### Application Properties

The `application.properties` file contains the following configuration:

```properties
spring.application.name=pokemon-project
spring.config.import=optional:file:.env[.properties]
spring.jpa.hibernate.ddl-auto=update
```

- **ddl-auto=update** - Automatically updates the database schema on startup
- Environment variables are loaded from the `.env` file

### Database Setup

The application automatically creates the necessary tables on startup due to Hibernate's `ddl-auto=update` setting. The following entities are created:

- **Pokemon** - Pokémon data
- **Move** - Move/attack data
- **Ability** - Pokémon abilities
- **User** - User accounts with authentication
- **Role** - User roles (e.g., USER, ADMIN)

## Running the Application

### Using Maven

1. **Build and run the application**
   ```bash
   mvn spring-boot:run
   ```

2. **Or build as JAR and run**
   ```bash
   mvn clean package
   java -jar target/pokemon-project-0.0.1-SNAPSHOT.jar
   ```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, access the interactive API documentation at:
- **Scalar UI** - `http://localhost:8080/scalar`

## Features

### Current Features
- **User Authentication** - JWT-based authentication and registration
- **Pokémon Management** - Create, read, update, and delete Pokémon data
- **Move Management** - Manage Pokémon moves with damage calculations
- **Ability Management** - Manage Pokémon abilities
- **Favorite Pokémon** - Users can mark Pokémon as favorites
- **Type System** - Support for 18 Pokémon types
- **Damage Categories** - Physical, Special, and Status move categories
- **API Documentation** - Interactive Swagger/Scalar UI
- **Error Handling** - Comprehensive global exception handling

### Security Features
- JWT Token-based authentication
- Password encoding using Spring Security
- Role-based access control
- Request validation using Jakarta validation
