API for frontend ChâTop
---
This project uses Java 21 with Maven and a MySQL database. 

You can find all needed dependencies in <strong>pom.xml</strong> file.


Create database
---

In a Mysql terminal

mysql -u username -p

CREATE database your_database_name;

USE your_database_name;


Copy the content of the <strong>chatop_tables.sql</strong> file that you can fin in resources folder.



Paste it in the MySQL terminal, then press enter.

Verify that the tables exists in the database with the command in the terminal:

SHOW TABLES;


You should have four tables: 

+----------------+
| messages       |
| rentals        |
| users          |
| users_seq      |
+----------------+


Install the project
---
- Clone the repository:

In a terminal, place yourself in the folder you want to place this code, then:

git clone https://github.com/Helloz18/OC-P3

- Go in application.properties file
- Copy the properties from line 1 to 12
- Paste them in a new file named env.properties in your resources folder
- Fill them with your connection's information

Run the project
---
- After you create the database and install the project on your computer:
- build the project: run command in terminal mvn package
- If you don't have <strong>maven</strong> installed, install it: https://maven.apache.org/download.cgi or use your IDE
- Place yourself in target folder and in terminal launch the jar: java -jar .\api-1.0.jar


Access project
---

- Server port is 3001
- You can access openApi through [Swagger](http://localhost:3001/swagger-ui/index.html#/)

