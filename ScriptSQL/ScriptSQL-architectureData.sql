/* Reconstruct Setup Prod DB */
DROP DATABASE IF EXISTS PayMyBuddy;
CREATE DATABASE PayMyBuddy;
use PayMyBuddy;

/* Create tables and reset if exists */
DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
  user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  firstname VARCHAR(30) NOT NULL,
  lastname VARCHAR(30) NOT NULL,
  city VARCHAR(30) NOT NULL,
  email VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL,
  balance DECIMAL(9,2) NOT NULL
);

DROP TABLE IF EXISTS `connection`;
CREATE TABLE connection (
  user_id INT NOT NULL,
  connection_id INT NOT NULL,
  KEY (connection_id),
  CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES user(user_id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_connections FOREIGN KEY (connection_id) REFERENCES user(user_id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `bankaccount`;
CREATE TABLE bankaccount (
  bankaccount_id INT AUTO_INCREMENT NOT NULL,
  iban_account VARCHAR(30) NOT NULL,
  bank_name VARCHAR(30) NOT NULL,
  user_id INT NOT NULL UNIQUE,
  PRIMARY KEY (bankaccount_id),
  CONSTRAINT FK_userid FOREIGN KEY (user_id) REFERENCES user(user_id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE transaction (
  transaction_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  amount DECIMAL(9,2) NOT NULL,
  description VARCHAR(90) NOT NULL,
  transactionfee DECIMAL(6,2) NOT NULL
);