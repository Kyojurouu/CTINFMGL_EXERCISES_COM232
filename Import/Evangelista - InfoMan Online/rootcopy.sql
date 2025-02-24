CREATE DATABASE TikTokDB
USE TikTokDB


CREATE TABLE adminUsers(
adminID INT AUTO_INCREMENT PRIMARY KEY,
adminUser VARCHAR(50),
adminPassword VARCHAR(50)
);

SELECT * FROM adminUsers;

INSERT INTO adminUsers
VALUES (1, "JM", "2023"),
	   (2, "Henry", "2024"),
       (3, "Chaebron", "2025");







CREATE TABLE users ( 
userName VARCHAR(50),
FName VARCHAR(50),
LName VARCHAR(50),
Email VARCHAR(50),
Pronoun VARCHAR (10),
userFollowing INT,
userFollowers INT
);


SELECT * FROM users;

