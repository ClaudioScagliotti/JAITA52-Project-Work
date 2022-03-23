-- CREATE DB & USER (requires root priviledges)

-- Crea il Db se non esiste
CREATE DATABASE IF NOT EXISTS `projectwork`;

-- seleziona il db
USE `projectwork`;

-- crea l'utente
#CREATE USER 'app_generation'@'localhost' IDENTIFIED BY 'generation_2022';

-- fornisce i privilegi all'utente 
GRANT ALL ON projectwork.* TO 'app_generation'@'localhost';

FLUSH PRIVILEGES;