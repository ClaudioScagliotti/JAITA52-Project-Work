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
`ins` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
`inizio_noleggio` DATE NOT NULL,
`fine_noleggio` DATE NOT NULL,
`stato` enum(Corrente, Annullato, Completato, Prenotato) DEFAULT Prenotato,
PRIMARY KEY (id),
FOREIGN KEY `fk_cliente` (`cliente_id`)
REFERENCES utente(utente_id),
FOREIGN KEY `fk_veicolo` (`veicolo_id`)
REFERENCES veicolo(id)
);
