# EMPDATABASE
# 👨‍💻 Employee Database Java App

A simple Java console application using **JDBC** to connect to **MySQL** and perform CRUD operations.

## 🧰 Tools Used

- Java
- JDBC
- MySQL
- Eclipse / VS Code

## 📦 Features

- Add new employees
- View all employees
- Update salary
- Delete employee by ID

## 🛠️ Setup

1. Install MySQL and create database/table:
   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;
   CREATE TABLE employees (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100),
     department VARCHAR(100),
     salary DOUBLE
   );

