# Personal Task Manager
A simple yet powerful task management application built with Spring Boot 3.x, Thymeleaf, and Bootstrap.

## Features
- ✅ Create, Read, Update, Delete tasks
- 📝 Task categories and priorities
- 🎯 Due date management
- 📊 Task status tracking
- 🎨 Responsive Bootstrap UI
- 💾 H2 in-memory database

## Tech Stack
- **Backend**: Spring Boot 3.5.5, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5, jQuery
- **Database**: H2 (in-memory)
- **Testing**: JUnit 5, Spring Boot Test
- **Build Tool**: Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run: `mvn spring-boot:run`
4. Open browser and go to: `http://localhost:8080`

### H2 Database Console
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:taskdb`
- Username: `sa`
- Password: `password`

## License
This project is licensed under the MIT License.