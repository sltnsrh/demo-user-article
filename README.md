# About
Spring Boot application with JWT-based security and H2 in-memory database, featuring CRUD operations for User and Article entities, and APIs for retrieving data based on specified conditions.<br />
<br />
**Technologies:**

* Java 8
* Maven
* H2 Database
* Flyway
* Spring Boot 2.7.10:
    * data-jpa
    * web
    * security
    * validation
* Jwt security

# Getting Started
* Clone repository to your local
* To build and run the project, navigate to the project directory and run the following commands:<br />
`> mvn clean install`<br />
`> mvnw spring-boot:run`
* Or you can run the app from your favorite IDE (such as IntelliJ IDEA, Eclipse, or NetBeans). 
* This will start the application on port 8080.
* After the starting you can open H2 DB interface by url: `http://localhost:8080/h2console`
  * JDBC URL: `jdbc:h2:mem:userdemodb`
  * User Name: `sa`
  * No password
* Import a Postman collection from included in the project file `UserDemo.postman_collection.json`
* If you want to login with existing in a DB user, use any email from `users` table and password: `password`
* Or you can register a new user on your own

# API
The following API endpoints are available:

* Register a new user: `POST /register`
* Login into app: `POST /login`
* Save a new article: `POST /articles`
* Get all users with age greater than a certain value: `GET /users/by-min-age?minAge={age}`
* Get all users with articles of a certain color: `GET users/by-article-color?articleColor={color}`
* Get all unique user names with more than three articles: `GET /users/names/by-most-articles-created`

# Security
This project uses JWT-based security. Token expiration period is 5 min. After you should re-login.
