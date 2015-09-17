CREATE DATABASE ASP;
USE ASP;

CREATE TABLE PBook(
	id INT AUTO_INCREMENT,
	name VARCHAR(52),
	phone BIGINT,
	address VARCHAR(255),
        photo longblob,
	PRIMARY KEY(id)
);

INSERT INTO PBook(name, phone, address) VALUES('Santosh Shukla', 8802509489, 'Wazirabad Delhi');
INSERT INTO PBook(name, phone, address) VALUES('Gopal Pandey', 9868967743, 'JagatPur Delhi');
INSERT INTO PBook(name, phone, address) VALUES('Krishna Pandey', 9013289570, 'JagatPur Delhi');
INSERT INTO PBook(name, phone, address) VALUES('Brajesh Sharma', 9654667092, 'Wazirabad Delhi');