CREATE DATABASE IF NOT EXISTS `projectwork`;
USE `projectwork`;
#CREATE USER 'app_generation'@'localhost' IDENTIFIED BY 'generation_2022';
GRANT ALL ON projectwork.* TO 'app_generation'@'localhost';
FLUSH PRIVILEGES;
CREATE TABLE IF NOT EXISTS `utente` (
`utente_id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(75) DEFAULT NULL,
`cognome` varchar(75) DEFAULT NULL,
`data_nascita` date DEFAULT NULL,
`email` varchar(50) NOT NULL,
`password` varchar(20) NOT NULL,
`ruolo` enum('RUOLO_ADMIN','RUOLO_UTENTE') NOT NULL,
PRIMARY KEY (`utente_id`),
KEY `k_email` (`email`)
);

INSERT INTO `utente`(nome,cognome,dataNascita,email,password,ruolo)
VALUES ('Paolo','Rossi','1994-06-07','admin@email.com','admin','RUOLO_ADMIN'),
('Carlo','Verdi','2001-03-19','utente@email.com','utente','RUOLO_UTENTE');

CREATE TABLE IF NOT EXISTS `categoria` (
`id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(25) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `alimentazione` (
`id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(25) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `veicolo` (
`id` int NOT NULL AUTO_INCREMENT,
`categoria_id` int NOT NULL,
`alimentazione_id` int NOT NULL,
`marca` varchar(50) NOT NULL,
`modello` varchar(100) NOT NULL,
`colore` varchar(20) NOT NULL,
`descrizione` varchar(255) NOT NULL,
`indirizzo` varchar(50) NOT NULL,
`coordinata_x` decimal(11,8) DEFAULT NULL,
`coordinata_y` decimal(11,8) DEFAULT NULL,
`disponibilita` boolean DEFAULT NULL,
`immagine` varchar(255) DEFAULT NULL,
PRIMARY KEY (id),
FOREIGN KEY `fk_categoria` (`categoria_id`)
REFERENCES categoria(id),
FOREIGN KEY `fk_alimentazione` (`alimentazione_id`)
REFERENCES alimentazione(id)
);

CREATE TABLE IF NOT EXISTS `prenotazione` (
`id` int NOT NULL AUTO_INCREMENT,
`cliente_id` int NOT NULL,
`veicolo_id` int NOT NULL,
`ins` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`inizio_noleggio` DATE NOT NULL,
`fine_noleggio` DATE NOT NULL,
`stato` enum('Corrente', 'Annullato', 'Completato', 'Prenotato') DEFAULT 'Prenotato',
PRIMARY KEY (id),
FOREIGN KEY `fk_cliente` (`cliente_id`)
REFERENCES utente(utente_id),
FOREIGN KEY `fk_veicolo` (`veicolo_id`)
REFERENCES veicolo(id)
);
CREATE DATABASE IF NOT EXISTS `projectwork`;
USE `projectwork`;
#CREATE USER 'app_generation'@'localhost' IDENTIFIED BY 'generation_2022';
GRANT ALL ON projectwork.* TO 'app_generation'@'localhost';
FLUSH PRIVILEGES;
CREATE TABLE IF NOT EXISTS `utente` (
`id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(75) DEFAULT NULL,
`cognome` varchar(75) DEFAULT NULL,
`data_nascita` date DEFAULT NULL,
`email` varchar(50) NOT NULL,
`password` varchar(20) NOT NULL,
`ruolo` enum('RUOLO_ADMIN','RUOLO_UTENTE') NOT NULL,
PRIMARY KEY (`utente_id`),
KEY `k_email` (`email`)
);

INSERT INTO `utente`(nome,cognome,dataNascita,email,password,ruolo)
VALUES ('Paolo','Rossi','1994-06-07','admin@email.com','admin','RUOLO_ADMIN'),
('Carlo','Verdi','2001-03-19','utente@email.com','utente','RUOLO_UTENTE');

CREATE TABLE IF NOT EXISTS `categoria` (
`id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(25) NOT NULL,
PRIMARY KEY (id)
);
INSERT INTO categoria (nome)
value('Auto'), ('Bicicletta'),('Monopattino');



CREATE TABLE IF NOT EXISTS `alimentazione` (
`id` int NOT NULL AUTO_INCREMENT,
`nome` varchar(25) NOT NULL,
PRIMARY KEY (id)
);
INSERT INTO alimentazione (nome)
value('elettrica'), ('diesel'), ('benzina'),('ibrida'), ('nessuna');

CREATE TABLE IF NOT EXISTS `veicolo` (
`id` int NOT NULL AUTO_INCREMENT,
`categoria_id` int NOT NULL,
`alimentazione_id` int NOT NULL,
`marca` varchar(50) NOT NULL,
`modello` varchar(100) NOT NULL,
`colore` varchar(20) NOT NULL,
`descrizione` varchar(255) NOT NULL,
`indirizzo` varchar(50) NOT NULL,
`coordinata_x` decimal(11,8) DEFAULT NULL,
`coordinata_y` decimal(11,8) DEFAULT NULL,
`disponibilita` boolean DEFAULT NULL,
`immagine` varchar(255) DEFAULT NULL,
PRIMARY KEY (id),
FOREIGN KEY `fk_categoria` (`categoria_id`)
REFERENCES categoria(id),
FOREIGN KEY `fk_alimentazione` (`alimentazione_id`)
REFERENCES alimentazione(id)
);
INSERT INTO veicolo (categoria_id,alimentazione_id,marca,modello,colore,descrizione,indirizzo,coordinata_x,coordinata_y,immagine)
VALUES (1,1,'Fiat','Panda','rosso','1200cc','Via Carlo Alberto 22,Torino',45.22325425,7.2342555,'FiatPanda.jpg'),
     (2,5,'Shimano','BiciXtreme','blu','cambio manuale','Via Roma 39,Torino',47.22325425,9.2342555,'Bici1.jpg'),
      (5,1,'BMW','iX','rosso','630 km di autonomia','Piazza Sabotino 39,Torino',48.22325425,9.2342555,'BMWiX.jpg');
CREATE TABLE IF NOT EXISTS `prenotazione` (
`id` int NOT NULL AUTO_INCREMENT,
`cliente_id` int NOT NULL,
`veicolo_id` int NOT NULL,
`ins` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`inizio_noleggio` DATE NOT NULL,
`fine_noleggio` DATE NOT NULL,
`stato` enum('Corrente', 'Annullato', 'Completato', 'Prenotato') DEFAULT 'Prenotato',
PRIMARY KEY (id),
FOREIGN KEY `fk_cliente` (`cliente_id`)
REFERENCES utente(utente_id),
FOREIGN KEY `fk_veicolo` (`veicolo_id`)
REFERENCES veicolo(id)
);

INSERT INTO prenotazione (cliente_id,veicolo_id,inizio_noleggio,fine_noleggio,stato)
VALUES(1,2,'2022-03-23','2022-03-30','Corrente');


INSERT INTO `utente`(nome,cognome,data_nascita,email,password,ruolo)
VALUES ('Paolo','Rossi','1994-06-07','admin@email.com','admin','RUOLO_ADMIN'),
('Carlo','Verdi','2001-03-19','utente@email.com','utente','RUOLO_UTENTE');

