User Management Application
Project Overview
A Spring Boot user management system with authentication, registration, and role-based access control using an in-memory H2 database.

Prerequisites
Java 17+
Maven
Postman (optional, for testing)
Setup Instructions
Clone the Repository:

bash
Copy code
git clone https://github.com/your-username/user-management-app.git
cd user-management-app
Build and Run:

bash
Copy code
mvn clean install
mvn spring-boot:run
Access the Application:

App URL: http://localhost:8080
H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)
Admin User Creation
Generate Encrypted Password: Add the following method to the main class to generate an encrypted password:

java
Copy code
@Bean
public CommandLineRunner generatePassword(PasswordEncoder passwordEncoder) {
return args -> System.out.println(passwordEncoder.encode("adminpassword"));
}
Insert Admin User: Use the H2 console (/h2-console) and run:

sql
Copy code
INSERT INTO users (username, password, email, role)
VALUES ('admin', '$2a$10$<encryptedpassword>', 'admin@example.com', 'ADMIN');
Endpoints
/register - Register new users
/login - User login
/admin/users - Admin access to manage users (view, update, delete)
