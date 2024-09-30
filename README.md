# Basic Ecommerce System for Microservices Architecture Studies

## :octocat: Members
[Alex Silva](https://github.com/alexlsilva7) | [Henrique Almeida](https://github.com/Dev-Henrique-Almeida) | [Leonardo Nunes](https://github.com/leonardonb) | [Tayane Cibely](https://github.com/TayaneCibely)

## :page_with_curl: About the Project
This project is designed as a study case to explore and implement the **microservices architecture** using **Spring Boot**. The system simulates a basic e-commerce platform, breaking down key features into independent microservices such as **Catalog**, **Pricing**, **Inventory**, and **Authentication**. Each service operates independently and communicates with others via REST APIs, RabbitMQ (for asynchronous messaging), and Eureka for service discovery.

The system provides key functionalities like product management, price determination, inventory control, and user authentication, aiming to showcase how different microservices can collaborate to form a cohesive system while maintaining loose coupling, scalability, and resilience.

## :mortar_board: Academic Context

This project was developed as part of the course **Tópicos Especiais em Engenharia de Software - 2024.1**, taught by **Prof. Igor Vanderlei** at **UFAPE (Universidade Federal do Agreste de Pernambuco)**.

### :dart: Goals:
- Understand and apply the microservices architecture principles.
- Implement communication between microservices using **REST** and **RabbitMQ**.
- Handle **resilience** with tools like **Resilience4j** to manage retries, circuit breakers, and fallbacks.
- Use **Spring Cloud** for service discovery with **Netflix Eureka** and centralized configuration management.

## :hammer_and_wrench: Technologies Used

- **Java 17** 
The project runs on Java 17, ensuring compatibility with the latest features and enhancements in the JDK.
[Download Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

- **Spring Boot** 
Spring Boot serves as the foundation of the microservices, offering easy-to-setup web applications.
[Read More](https://spring.io/projects/spring-boot)

- **Spring Data**
Spring Data simplifies the interaction with databases, handling CRUD operations and repository patterns.
[Learn More](https://spring.io/projects/spring-data)
- **Spring Security**
Used for securing endpoints and handling authentication and authorization, integrated with Keycloak for managing user access.
[Learn More](https://spring.io/projects/spring-security)
- **Spring Cloud Eureka**
Eureka is used for service discovery, enabling each microservice to register and locate other services dynamically.
[Learn More](https://spring.io/projects/spring-cloud-netflix)

- **Spring Web & Spring Web MVC**
Provides RESTful web services for inter-microservice communication and client-server interactions.
[Read More](https://spring.io/projects/spring-web)

- **Resilience4j**
A resilience library used for circuit breakers, retries, and time limiters to make the system more resilient to failures.
[Getting Started](https://resilience4j.readme.io/docs/getting-started)

- **RabbitMQ**
RabbitMQ is used for asynchronous message handling between microservices, especially for communication between the product and inventory services.
[Learn More](https://www.rabbitmq.com/)

- **PostgreSQL**
Each microservice uses PostgreSQL as its database, isolated per service to maintain database autonomy.
[PostgreSQL Docs](https://www.postgresql.org/)

- **Docker & Docker Compose**
The system uses Docker for containerizing the microservices, with **Docker Compose** orchestrating multi-container applications, enabling each microservice to run independently.
[Learn More](https://www.docker.com/)

- **Keycloak**
For centralized user authentication and authorization, Keycloak is integrated with the microservices to handle token-based security (JWT).
[Keycloak Documentation](https://www.keycloak.org/)

## :memo: Microservices Architecture

The system consists of the following key microservices:

1. **Catalog Service**: Manages product categories and details.
2. **Price Service**: Handles product pricing and discount policies.
3. **Inventory Service**: Manages product stock and warehouse information.
4. **Authentication Service**: Provides user authentication and authorization using Keycloak.
5. **Gateway Service**: Routes API requests to the appropriate microservice using Spring Cloud Gateway.
6. **Discovery Service**: Facilitates service discovery and registration using Netflix Eureka.


## :gear: Features Implemented

- **Microservice Communication**: Services communicate via REST APIs and RabbitMQ for asynchronous messaging.
- **Service Discovery**: Using Eureka to enable dynamic service registration and discovery.
- **Resilience**: Implemented Resilience4j for circuit breakers, retries, and fallbacks.
- **Authentication**: JWT-based authentication with Keycloak.
- **Containerization**: Every microservice is containerized with Docker, allowing for easy scalability and isolation.
- **Database Separation**: Each microservice uses a separate PostgreSQL database for persistence.

## :construction: Status

**Finished** – The project is complete and serves as a study resource for microservices architecture.
