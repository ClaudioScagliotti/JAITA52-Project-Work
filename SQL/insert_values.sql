-- POPULATE DB

INSERT INTO alimentazione (nome)    
      value ('elettrica'), 
            ('diesel'), 
            ('benzina'),
            ('ibrida'),
            ('nessuna');

INSERT INTO categoria (fattore, nome)
      value ('100', 'bicicletta'),
            ('83', 'bicicletta elettrica'),
            ('96', 'monopattino'),
            ('51', 'auto elettrica'),
            ('33', 'auto ibrida'),
            ('0', 'auto benzina'),
            ('0', 'auto diesel');


INSERT INTO veicolo 
      (categoria_id,alimentazione_id,marca,modello,colore,descrizione,indirizzo,
            coordinata_x,coordinata_y,immagine)
                  VALUES     
                        (1,1,'Fiat','Panda','rosso','1200cc','Via Carlo Alberto 22,Torino',
                              45.22325425,7.2342555,'FiatPanda.jpg'),
                        (2,5,'Shimano','BiciXtreme','blu','cambio manuale','Via Roma 39,Torino',
                              47.22325425,9.2342555,'Bici1.jpg'),
                        (3,1,'BMW','iX','rosso','630 km di autonomia','Piazza Sabotino 39,Torino',
                              48.22325425,9.2342555,'BMWiX.jpg');   






INSERT INTO prenotazione (cliente_id,veicolo_id,inizio_noleggio,fine_noleggio,stato)
      VALUES (1,2,'2022-03-23','2022-03-30','Corrente'),
		(2,7,'2022-03-20','2022-03-27','Completato'),
            (2,9,'2022-03-15','2022-04-30','Annullato'),
            (1,4,'2022-03-23','2022-04-30','Annullato');


INSERT INTO utente (nome,cognome,data_nascita,email,password,ruolo)
      VALUES 
            ('Paolo','Rossi','1994-06-07','admin@email.com','admin','RUOLO_ADMIN'),
            ('Carlo','Verdi','2001-03-19','utente@email.com','utente','RUOLO_UTENTE');