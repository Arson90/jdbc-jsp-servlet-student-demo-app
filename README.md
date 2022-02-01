# jdbc-jsp-servlet-student-demo-app

## Description
Example webservice where I used connection to MySQL using JDBC. There is a servlet with CRUD operations. Also using JSP, HTML, Bootstrap. I used Tomcat 9 to run app.

## Index
* [Architecture Diagram](#architecture-diagram)
* [JSP](#jsp)
* [Prerequisite](#prerequisite)
* [Installing Tools](#installing-tools)
* [MySQL](#mysql)
* [Functionality](#functionality)
  
## Architecture Diagram
![ArchitectureDiagram](https://user-images.githubusercontent.com/37801354/150788631-458788ad-f4b9-4c8c-9187-3c9a6f40544e.jpg)

## 

## JSP
* JSP Scripting Elements
    * JSP Declaration - <%!  field or method declaration %> 
    * JSP Expression - <%=  statement %>
    * JSP Scriptlet - <%  java source code %> 
* JSP Standard Tag Library (JSTL)
    * Core
    * I18N
    * Functions
    * XML
    * SQL

## Prerequisite
* Install IntelliJ IDEA
* Install JDK (Java Development Kit)
* MySQL Server
* MySQL_WORKBRENCH
* Java Application Server - Tomcat 9

## Installing Tools
### Linux
* This is a [link](https://github.com/Arson90/installation-intellij-idea-and-jdk-linux) how to install Intellij_IDEA and JDK
* This is a [link](https://github.com/Arson90/installation-mysql-server-and-mysql-workbench-linux) how to install MySQL Server and MySQL Workbench

## MySQL
1. Created example user and granted all provileges to DB.
```
CREATE USER 'webstudent'@'localhost' IDENTIFIED BY 'webstudent';
```
```
GRANT ALL PRIVILEGES ON * . * TO 'webstudent'@'localhost';
```
2. Created db and schema.
```
CREATE DATABASE IF NOT EXISTS web_student;
```
```
DROP TABLE IF EXISTS student;
```
```
CREATE TABLE student (
    id_student INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    student_book_number INT,
    PRIMARY KEY(id_student)
);
```
3. Generated examples data, saved as a CSV file and loaded to DB.
```
LOAD DATA LOCAL INFILE 'file existing csv file, example: /home/test/Desktop/student_data.csv'
INTO TABLE student
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
```
IF YOU HAVE A PROBLEM YOU CAN LOG IN TO MYSQL IN TERMINAL USING THIS
```
mysql --local-infile=1 -u root -p
```
AND THEN YOU CAN TRY LOAD DATA USING TERMINAL.

## Functionality
### Used query
* Adding student:
```
String insertQuerySQL = "INSERT INTO student (first_name, last_name, email, student_book_number) " + "VALUES (?, ?, ?, ?);";
```
* Reading students
```
String selectQuerySQL = "SELECT * FROM student";
```
* Reading student by id
```        
String selectByIdQuerySQL = "SELECT * FROM student WHERE id_student = ?;";
```
* Updating student by id
```
String updateStudentByIdQuerySQL = "UPDATE student SET first_name = ?, last_name = ?, email = ?, student_book_number = ? WHERE id_student = ?;";
```
* Removing student by id
```
String deleteStudentByIdQuerySQL = "DELETE FROM student WHERE id_student = ?;";
```
### View
![obraz](https://user-images.githubusercontent.com/37801354/151953377-7343f3ab-a6cb-4e3d-9444-1ac2e424fae1.png)
