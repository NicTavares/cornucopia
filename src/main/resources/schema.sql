USE cpsc_304;

DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Equipment;
DROP TABLE IF EXISTS Administrator;

DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS Tag;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS Usr;

CREATE TABLE Equipment(
	name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Usr (
       UUID INT PRIMARY KEY,
       birthday DATE,
       email VARCHAR(255) NOT NULL,
       username VARCHAR(255) NOT NULL,
       name VARCHAR(255) NOT NULL,
       password VARCHAR(255) NOT NULL,
       city VARCHAR(255) ,
       postalCode VARCHAR(255) ,
       UNIQUE (email),
       UNIQUE (username)
);

CREATE TABLE Administrator (
	UUID INT PRIMARY KEY,
	adminLevel VARCHAR(255),
	password  VARCHAR(255),
	email VARCHAR(255),
	FOREIGN KEY (UUID) references Usr(UUID)
		ON DELETE CASCADE
);

CREATE TABLE Course (
	UUID INT PRIMARY KEY,
	text LONGTEXT,
	length INT,
	name VARCHAR(255) NOT NULL,
	requirementName VARCHAR(255),
	creatorUUID INT,
	UNIQUE (name),
	FOREIGN KEY (requirementName) REFERENCES Equipment(name)
		ON DELETE SET NULL,
	FOREIGN KEY (creatorUUID) REFERENCES Administrator(UUID)
		ON DELETE SET NULL
);

CREATE TABLE Tag(
	name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Recipe (
	UUID INT PRIMARY KEY,
	text LONGTEXT NOT NULL,
	averageScore FLOAT,
	estimatedTime FLOAT,
	uploaderUUID INT,
	FOREIGN KEY (uploaderUUID) REFERENCES Usr(UUID)
	ON DELETE SET NULL

);

CREATE TABLE Ingredient(
	name VARCHAR(255) PRIMARY KEY
);



CREATE TABLE Message (
    UUID INT PRIMARY KEY,
    Text VARCHAR(255) NOT NULL,
    senderUUID INT,
    sentTime TIME NOT NULL,
    receiverUUID INT,
    UNIQUE (senderUUID,receiverUUID,sentTime),
    FOREIGN KEY (senderUUID) REFERENCES Usr(UUID)
    ON DELETE CASCADE,
    FOREIGN KEY (receiverUUID) REFERENCES Usr(UUID)
    ON DELETE CASCADE
);
