# Blog-Platform

Welcome to the Ktor Backend for a Blog Platform! This repository serves as the backend for a dynamic and efficient blogging platform powered by Ktor, a lightweight and flexible Kotlin web application framework, and utilizes MySQL as the database for data storage.

## Key Features

- Seamless API endpoints for managing blog posts and comments.
- Integration with MySQL database for robust data storage and retrieval.
- Efficient routing and request handling with Ktor's lightweight and asynchronous architecture.

Whether you're building a personal blog, a content management system, or a collaborative writing platform, this Ktor-based backend provides a strong foundation to get started.

## Getting Started

To get started with this backend, follow these steps:

1. Clone this repository to your local machine.
2. Configure your MySQL database settings:

   ```sql
   create database Blog_Platform;
   use Blog_Platform;
   
   CREATE TABLE blog (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(100),
       content VARCHAR(5000),
       nview INT,
       nlike INT
   );
   
   CREATE TABLE commenter (
       blogid INT,
       commenter VARCHAR(5000)
   );
   
4. Build and run the Ktor application.
5. Explore the API endpoints to manage blog posts and comments.

Happy coding!
