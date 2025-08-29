# 🏛️ AGORA – Forum Back-end System

**Status:** In development 🚧

AGORA is a **back-end** application for online forums. The name "AGORA" comes from the public square in Ancient Greek cities, where people gathered to discuss, debate, and exchange ideas — exactly the goal of this project.  

The purpose of AGORA is to be **easily replicable**: if you want to create multiple different forums, you just replicate the back-end and create a different front-end for each forum, without changing the server logic.

---

## Technologies Used

- **Java 23 / Spring Boot** – A robust framework for building modern, scalable, and productive back-end applications.  
- **H2 Database (in development)** – In-memory database for fast testing; will be replaced by **PostgreSQL** in production.  
- **ULID (Universally Unique Lexicographically Sortable Identifier)** – Orderable unique identifiers as an alternative to UUIDs, facilitating debugging, logging, and database indexing.  
- **Domain-Driven Design (DDD)** – Domain-oriented architecture, separating business rules, application logic, and infrastructure, ensuring easy maintenance and application evolution.

> In the future, new technologies will be implemented, such as PostgreSQL, JWT-based authentication (Spring Security), and caching with Redis.

> To run the application, make sure all dependencies are installed with `./mvnw clean install`, then start it with `./mvnw spring-boot:run` (or if you have Maven installed, just `mvn clean install` and `mvn spring-boot:run`).

[View Provisional Documentation](documentation.md)
