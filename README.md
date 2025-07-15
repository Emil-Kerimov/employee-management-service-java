
# Employee Management Service

A web service for managing employees and their professional skills.

## Technology Stack
-   **Language**: Java 17
-   **Framework**: Spring Boot 3.5.3
-   **Data Storage**: In-memory (HashMap)
-   **API Documentation**: OpenAPI 3 (Swagger UI)
-   **Build Tool**: Maven
-   **Containerization**: Docker

## API Endpoints
API documentation is available at: `http://localhost:8080/swagger-ui.html`


| Method   | API                                          | Опис                                                                 |
|----------|----------------------------------------------|----------------------------------------------------------------------|
| GET      | `/api/employees`                             | Get all employees                                         |
| GET      | `/api/employees/{id:guid}`                   | Get an employee by ID                                         |
| POST     | `/api/employees`                             | Create an employee                                               |
| PUT      | `/api/employees/{id:guid}`                   | Update an employee                                                |
| DELETE   | `/api/employees/{id:guid}`                   | Delete an employee                                               |
| GET      | `/api/employees/{id:guid}/skills-set`        | Get an employee’s skills and proficiency levels                |
| POST     | `/api/employees/{id:guid}/skills-set`        | Assign skills and proficiency levels to an employee                   |
|          |                                              |                                                                      |
| GET      | `/api/skill-categories`                      | Get all skill categories                                               |
| GET      | `/api/skill-categories/{category_id:int}`    | Get a skill category by ID                                             |
| POST     | `/api/skill-categories`                      | Create a skill category                                                   |
| PUT      | `/api/skill-categories/{category_id:int}`    | Update a skill category                                                    |
| DELETE   | `/api/skill-categories/{category_id:int}`    | Delete a skill category                                                   |
|          |                                              |                                                                      |
| GET      | `/api/skill-categories/{category_id:int}/skills` | Get skills in a category                                     |
| GET      | `/api/skill-categories/{category_id:int}/skills/{id:int}` | Get skill details by ID                  |
| POST     | `/api/skill-categories/{category_id:int}/skills` | Create a skill in a category                                    |
| PUT      | `/api/skill-categories/{category_id:int}/skills/{id:int}` | Update skill details                       |
| DELETE   | `/api/skill-categories/{category_id:int}/skills/{id:int}` | Delete a skill                          |

##  Data Models

### `Employee`
```java
{
  "id": "UUID",
  "firstName": "String",
  "lastName": "String",
  "title": "String",
  "birthday": "LocalDate"
}
```

### `EmployeeSkill`
```java
{
  "skillId": "Integer",
  "level": "BEGINNER/INTERMEDIATE/ADVANCED/EXPERT"
}
```
### `Skill`
```java
{
  "Id": "Integer",
  "name": "String",
  "CategoryId": "Integer",
}
```
### EmployeeSkillSet
```java
{
  "employeeId": "UUID",
  "categorySkills": {
    "1": [
      {
        "skillId": 101,
        "level": "EXPERT"
      }
    ]
  }
}
```

## Project Setup

### Local Run
```bash
./mvnw spring-boot:run
```

### Docker Build
```bash
docker build --platform linux/amd64 -t employee-api:0.0.1 -f Dockerfile .
docker run -d -p 8080:8080 --name employee-container employee-api:0.0.1 --settings.applicationVersion=2.0.0 --settings.testMode.enabled=true
```

## Configuration

Environment variables:

-   `applicationVersion`  - App version (default:  `1.0.0`)

-   `TestMode`  - Test mode toggle (`true`/`false`)


Check config:
```bash
GET /app/info
```
## Example Requests

### Create Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "title": "Developer",
    "birthday": "1990-01-01"
  }'
```

### Update Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "title": "Developer",
    "birthday": "1990-01-01"
  }'
  ```
### Create Skill Category
```bash
curl -X 'POST' 'http://localhost:8080/skill-categories' \
 -H 'accept: */*' \
  -H 'Content-Type: application/json' \
   -d '{
    "name": "Programming", 
    "area": "Engineering" 
    }'
  ```
### Get All Categories
```bash
curl -X 'GET' \
 'http://localhost:8080/skill-categories' \
  -H 'accept: */*'
  ```
### Delete Category (ID: 1)
```bash
curl -X 'DELETE' \
 'http://localhost:8080/skill-categories/1' \
  -H 'accept: */*'
  ```
### Update Category (ID: 2)
```bash
curl -X 'PUT' \
 'http://localhost:8080/skill-categories/2' \
  -H 'accept: */*' \
   -H 'Content-Type: application/json' \
    -d '{
     "name": "Programming", 
     "area": "Engineering" 
     }'
  ```
### Get Category (ID: 2)
```bash
curl -X 'GET' \
 'http://localhost:8080/skill-categories/2' \
  -H 'accept: */*'
  ```
### Create Skill in Category (ID: 2)
```bash
curl -X 'POST' \
 'http://localhost:8080/skill-categories/2/skills' \
  -H 'accept: */*' \
   -H 'Content-Type: application/json' \
    -d '{
     "name": "database skills" 
     }'
  ```