USE cpsc_304;

DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Equipment;
DROP TABLE IF EXISTS Administrator;
DROP TABLE IF EXISTS Users;

CREATE TABLE Equipment(
	name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Users (
       UUID INT PRIMARY KEY,
       birthday DATE,
       email VARCHAR(255) NOT NULL,
       username VARCHAR(255) NOT NULL,
       name VARCHAR(255) NOT NULL,
       password VARCHAR(255) NOT NULL,
       UNIQUE (email),
       UNIQUE (username)
);

CREATE TABLE Administrator (
	admin_UUID INT PRIMARY KEY,
	admin_level VARCHAR(255), 
	FOREIGN KEY (admin_UUID) references Users(UUID)
		ON DELETE CASCADE
);

CREATE TABLE Course (
	course_ID INT PRIMARY KEY, 
	text LONGTEXT, 
	course_length INT, 
	course_name VARCHAR(255) NOT NULL, 
	requirement_name VARCHAR(255), 
	creator_UUID INT,
	UNIQUE (course_name),
	FOREIGN KEY (requirement_name) REFERENCES Equipment(name)
		ON DELETE SET NULL,
	FOREIGN KEY (creator_UUID) REFERENCES Administrator(admin_UUID)
		ON DELETE SET NULL
);



