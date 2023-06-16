# News API

### Table of Contents
1. [Introduction](#introduction)
2. [Project Structure](#project-structure)
3. [Setup and Installation](#setup-and-installation)
4. [API Endpoints](#api-endpoints)
5. [Testing](#testing)

## Introduction
This README document provides an overview and guidance for a Assignment for STRONG TEAM. The project demonstrates the usage of the Spring framework to build a RESTful API. 

## Project Structure
The project structure follows standard Maven conventions for a Java project, with the Spring framework integrated. Here is an overview of the key directories and files:

```
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.github.jumagaliev1.backendAssignment
│ │ │ ├── config
│ │ │ ├── controller
│ │ │ ├── exception
│ │ │ ├── model
│ │ │ │ ├── entity
│ │ │ │ ├── request
│ │ │ | └── response
│ │ │ ├── repository
│ │ │ ├── service
│ │ │ ├── security
│ │ │ ├── util
│ │ │ └── BackendAssignmentApplication.java
│ │ └── resources
│ │ └── application.properties
│ └── test
├── pom.xml
├── docker-compose.yml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── README.md
└── .gitignore
```
- `src/main/java`: Contains the main Java source code files.
  - `com.github.jumagaliev1.backendAssignment`: The base package for the project.
  - `config`: Contains configuration files for Spring.
  - `controller`: Holds the REST API controllers.
  - `exception`: Contains custom exception classes.
  - `model`: Holds the domain models or entities.
  - `repository`: Contains repositories for data access.
  - `service`: Contains business logic and services.
  - `BackendAssignmentApplication.java`: The entry point for the application.
- `src/main/resources`: Contains the application properties and resource files.
- `src/test/java`: Contains the unit and integration tests.
- `pom.xml`: Maven configuration file for managing dependencies and build settings.
- `README.md`: The documentation file you're currently reading.
- `.gitignore`: Specifies files and directories to be ignored by version control.
- `docker-compose.yml`: Docker compose instructions.

## Setup and Installation
To set up the project, follow these steps:
1. Clone the project repository: `git clone https://github.com/jumagaliev1/backendAssignment.git`
2. Install Docker, docker compose engine.
3. `docker compose up --build app` (if have errors try `docker compose up`)

## API Endpoints
The project includes the following REST API endpoints that can be accessed for various operations:
### User
- `POST /sign-up`: Creates new User.
```
  {
    "username":"alibi",
    "email":"alibi@gmail.com",
    "password":"123456789a"
  }
```
- `POST /sign-in`: Authentication User. Give Bearer Token
```
  {
    "username":"alibi",
    "password":"123456789a"
  }
```
Response
```
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudXJkYSIsImlhdCI6MTY4NDMxOTUyNywiZXhwIjoxNjg0NDA1OTI3fQ.8qI3ykdd1vEobLwdrwOWPqA6liDkVWcUojCXfvAGElDTLMfV4tjUuX4s1eUadlW0CWjZlddnBSDQ-MeoQvx5gA",
    "type": "Bearer",
    "id": 35,
    "username": "alibi",
    "email": "alibi@gmail.com",
    "roles": [
        "USER"
    ]
  }
```
### News (Only authenticated users)
- `GET /api/news`: Retrives All News (Allowed Pagination).
- `GET /api/news?page=1`: Retrives All News in first page.
- `POST /api/news`: Create new News.
```
  {
    "title":""Breaking News: Earthquake Strikes City, Causing Damage",
    "content": "In a shocking turn of events, a powerful earthquake measuring 6.5 on the Richter scale hit the city...",
    "source_id":1,
    "topic_id":[1]
  }
```
- `PUT /api/news/{id}`: Update News.
- `GET /api/news/{id}`: Get News by Id.
- `GET /api/news/stat`: Get News statistics.
- `GET /api/news/by-source/{sourceId}`: Get News by Source Id (Allowed Pagination).
- `GET /api/news/by-source/{topicId}`: Get News by Topic Id (Allowed Pagination).
### News source (Only authenticated users)
- `GET /api/news-sources`: Get All News Sources (Allowed Pagination).
- `POST /api/news-sources`: Creates new News Source.
```
  {
    "name":"Qazaqstan"
  }
```
- `GET /api/news-sources/{id}`: Get News Source by Id.
- `PUT /api/news-sources/{id}`: Update News Source.
- `DELETE /api/news-sources/{id}`: Delete News Source.
### News topic (Only authenticated users)
- `GET /api/news-topics`: Get All News Topics (Allowed Pagination).
- `POST /api/news-topics`: Creates new News Topics.
```
  {
    "name":"Business"
  }
```
- `GET /api/news-topics/{id}`: Get News Topics by Id.
- `PUT /api/news-topics/{id}`: Update News Topics.
- `DELETE /api/news-topics/{id}`: Delete News Topics.

## Testing 
The project includes tests to ensure the correctness of the application's functionality. To run the tests, execute the following command:

```
mvn test
```
