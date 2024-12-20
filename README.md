# Learning Navigator Application

The Learning Navigator Application is a Spring Boot-based project for managing students, exams, and their registrations. This project uses Docker for containerization and MySQL as its database.

---

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Prerequisites](#prerequisites)
4. [Setup and Installation](#setup-and-installation)
5. [API Endpoints](#api-endpoints)
6. [Docker Usage](#docker-usage)
7. [License](#license)

---

## Features

- Register students and exams.
- Manage student-exam registrations.
- REST API with CRUD operations.
- Dockerized deployment.

---

## Technologies Used

- **Backend**: Spring Boot (Java 23)
- **Database**: MySQL
- **Containerization**: Docker and Docker Compose
- **Build Tool**: Gradle
- **Persistence Framework**: JPA/Hibernate

---

## Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- Java 23 SDK
- Gradle 8 or later

---

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AnirudhSharma777/LearningNavigator.git
   cd learningNavigator
   ```

2. Ensure you have Docker and Docker Compose installed.

3. Start the application using Docker Compose:
   ```bash
   docker-compose up -d
   ```

4. Access the application:
   - **API**: `http://localhost:8080`

5. Verify MySQL:
   - Host: `localhost`
   - Port: `3307`
   - Database: `lmsDB`
   - Username: `user`
   - Password: `password`

---

## API Endpoints

### Student API

| Method | Endpoint                     | Description                     |
|--------|-------------------------------|---------------------------------|
| GET    | `/api/students`              | Get all students               |
| GET    | `/api/students/{id}`         | Get a specific student by ID   |
| POST   | `/api/students`              | Add a new student              |
| PUT    | `/api/students/{id}`         | Update a student by ID         |
| DELETE | `/api/students/{id}`         | Delete a student by ID         |

### Exam Registration

| Method | Endpoint                                      | Description                           |
|--------|----------------------------------------------|---------------------------------------|
| POST   | `/register-exam/{student_id}/{exam_id}`       | Register a student for an exam       |

---

## Docker Usage

### Build and Start
1. **Build the Docker image** (if not already done):
   ```bash
   docker build -t learning-navigator .
   ```

2. **Start the containers**:
   ```bash
   docker-compose up -d
   ```

3. **Stop the containers**:
   ```bash
   docker-compose down
   ```

### Useful Docker Commands
- List running containers:
  ```bash
  docker ps
  ```
- Access MySQL container:
  ```bash
  docker exec -it mysql-container mysql -uuser -ppassword
  ```

---

## License

This project is licensed under the [MIT License](LICENSE).

---

Let me know if you'd like help tweaking any specific sections! 😊
