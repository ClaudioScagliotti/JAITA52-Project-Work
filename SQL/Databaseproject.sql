INSERT INTO veicolo 
      (categoria_id,alimentazione_id,marca,modello,colore,descrizione,indirizzo,
            coordinata_x,coordinata_y,immagine, visibile)
                  VALUES     
                        (1,4,'Fiat','Panda','blu','1269cc, velocità massima 160 Km/h','Via Carlo Alberto 22,Torino',
                              45.22325425,7.2342555,'/img/veicoli/citylife.jpg', 1),
                        (2,1,'Shimano','BiciXtreme','rossa','700 km di autonomia','Via Roma 39,Torino',
                              45.22325425,9.2342555,'/img/veicoli/e-bike1.jpg'),
                        (2,1,'BMX','iX','arancione','50 km di autonomia','Piazza Sabotino 39,Torino',
                              45.096675, 7.712280,'/img/veicoli/E-bike.jpg', 1),  
                        (1,1,'Tesla','Model S','Rossa','1020cc, velocità massima 322 Km/h','Via Gramsci 12,Torino',
                              45.040056, 7.669934,'/img/veicoli/modelS.jpg', 1),
                        (3,1,'MonoP','300L','Nero','Velocità massima 25 Km/h, autonomia 45 km','Via Vittorio Alfieri 32,Torino',
                             45.077331, 7.656298,'/img/veicoli/mono.jpg', 1),
                        (1,4,'Xiaomi','Scoter Pro','Nero','Velocità massima 25 Km/h, autonomia 45 km','Via Carlo Alberto 22,Torino',
                        45.078453, 7.629513,'/img/veicoli/xiaomi.jpg', 1),
                        (3,1,'Xiaomi','Essential','Nero','Velocità massima 20 Km/h, autonomia 40 km','Via Pietro Micca 6,Torino',
                              45.061775, 7.625265,'/img/veicoli/xiaomiesse.jpg', 1),
                        (1,1,'Volkswagen','ID.3','Bianco','Velocità massima 160 Km/h, autonomia 424 km','Via G.Gigante 72,Torino',
                             45.087063, 7.669570,'/img/veicoli/ID.jpg', 1),
                        (1,2,'Mazda','Cx 60','Bianco','2500cc, velocità massima 200 Km/h','Via Gramsci 12,Torino',
                              45.22325425,7.2342555,'/img/veicoli/mazda.jpg', 1),
                        (1,3,'Seat','Arona','Nero','1000cc, velocità massima 160 Km/h','Via Giuseppe Barbaroux 33,Torino',
                             45.025740, 7.624485,'/img/veicoli/arona.jpg', 1),
                        (2,1,'Vedra','Wave','Nero','70 km di autonomia','Corso vercelli 25,Torino',
                             45.064180, 7.696408,'/img/veicoli/vedra.jpg'),
                        (1,3,'Volkswagen','Polo','Bianco','Velocità massima 160 Km/h, 1000cc','Corso Novara 32,Torino',
                              45.103427, 7.687120,'/img/veicoli/polo.jpg');

INSERT INTO prenotazione (cliente_id,veicolo_id,inizio_noleggio,fine_noleggio,stato)
      VALUES (2,1,'2022-04-03','2022-04-05','Corrente'),
		(2,2,'2022-03-20','2022-03-27','Completato'),
            (2,3,'2022-03-15','2022-03-20','Annullato'),
            (2,4,'2022-03-23','2022-04-10','Corrente'),
		(2,5,'2022-02-20','2022-03-27','Completato'),
            (2,6,'2022-01-15','2022-02-21','Annullato'),
            (2,7,'2022-01-23','2022-01-30','Completato'),
             (2,8,'2022-03-15','2022-04-25','Annullato'),
            (2,9,'2022-03-23','2022-05-03','Corrente'),
		(2,10,'2022-02-20','2022-03-27','Completato'),
            (2,1,'2022-01-15','2022-04-30','Annullato'),
            (2,2,'2022-01-23','2022-03-30','Completato');

INSERT INTO utente (nome,cognome,data_nascita,email,password,ruolo)
      VALUES 
            ('Paolo','Rossi','1994-06-07','admin@email.com','admin','RUOLO_ADMIN'),
            ('Carlo','Verdi','2001-03-19','utente@email.com','utente','RUOLO_UTENTE');