# Spring Blog

A RESTful blogging platform built with Spring Boot.

**Project Reference:** https://roadmap.sh/projects/blogging-platform-api

## Tech Stack

- **Java** 21
- **Spring Boot** 3.4.11
- **Spring Web** - REST API
- **Spring Data JPA** - Database access
- **PostgreSQL** - Database
- **Lombok** - Boilerplate reduction
- **Validation** - Input validation
- **Maven** - Build tool

## Features

- Create, read, and delete blog posts
- Tag support with many-to-many relationships
- Category organization
- Search/filter posts by term (title, content, category)
- Input validation
- Global exception handling with structured error responses
- Health check endpoint

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/posts` | Get all blog posts |
| GET | `/api/v1/posts?term={search}` | Filter posts by term |
| GET | `/api/v1/posts/{post_id}` | Get post by ID |
| POST | `/api/v1/posts` | Create new post |
| DELETE | `/api/v1/posts/{post_id}` | Delete post |
| GET | `/api/v1/health` | Health check |

## Request/Response Format

### Create Post (POST /api/v1/posts)

```json
{
  "title": "My First Blog Post",
  "content": "This is the content of my blog post.",
  "category": "Technology",
  "tags": ["java", "spring", "tutorial"]
}
```

### Response

```json
{
  "id": 1,
  "title": "My First Blog Post",
  "content": "This is the content of my blog post.",
  "category": "Technology",
  "tags": ["java", "spring", "tutorial"],
  "createdAt": "2026-03-16T10:30:00",
  "updatedAt": "2026-03-16T10:30:00"
}
```

## Database Configuration

Update `src/main/resources/application.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_blog
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Build & Run

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

The application runs on `http://localhost:8080`

## Project Structure

```
src/main/java/com/vignesh/spring_blog/
├── SpringBlogApplication.java    # Main entry point
├── controller/
│   ├── BlogController.java       # REST endpoints
│   └── Health.java              # Health check
├── service/
│   └── BlogService.java         # Business logic
├── repository/
│   ├── BlogRepository.java      # Data access
│   └── TagRepository.java       # Tag data access
├── entity/
│   ├── Blog.java                # Blog entity
│   └── Tag.java                 # Tag entity
├── dto/
│   ├── BlogPostDTO.java         # Request DTO
│   ├── BlogResponseDTO.java     # Response DTO
│   └── ErrorResponseDTO.java    # Error DTO
├── util/
│   └── ResponseFormatter.java   # DTO conversion
└── exception/
    └── GlobalExceptionHandler.java  # Exception handling
```

## License

MIT
