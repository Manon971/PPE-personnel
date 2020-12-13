drop database if exists MDL;

create database MDL;


CREATE TABLE LIGUE
(
	id_ligue INT,
	nom_ligue VARCHAR(50),
	CONSTRAINT PK_LIG PRIMARY KEY(id_ligue)
) ENGINE=INNODB;


CREATE TABLE EMPLOYE
(
	id_employe INT,
	nom_employe VARCHAR (25),
	prenom_employe VARCHAR (25),
	mail_employe VARCHAR (50),
	password_employe VARCHAR (25),
	date_arrivee DATE,
	date_depart DATE,
	est_admin boolean,
	est_root boolean,
	CONSTRAINT PK_EMP PRIMARY KEY(id_employe)
) ENGINE=INNODB;
