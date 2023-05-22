CREATE DATABASE gestionvols;
USE login;

create TABLE users(
ID INT primary key auto_increment,
USERNAME VARCHAR(20)NOT NULL,
PASSWORD VARCHAR(20)NOT NULL,
role  VARCHAR(20)NOT NULL
);

INSERT INTO users(USERNAME,PASSWORD,ROLE) values ('admin','123','admin');