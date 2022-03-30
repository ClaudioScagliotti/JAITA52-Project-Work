package com.group.projectwork.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;

public interface PrenotazioneDB extends JpaRepository<Prenotazione, Integer> {
	List<Prenotazione> findAllByUtente (Utente u);
	@Query(nativeQuery = true, value = 
			"select * from prenotazione "
			+ "where veicolo_id=?1 and "
			+ "(stato='Corrente' or stato='Prenotato')")
	List<Prenotazione> findPrenotazioniAttive (int veicolo_id);
	List<Prenotazione> findAllByVeicolo(Veicolo v);
	List<Prenotazione> findAllByInizio (Date d);
	List<Prenotazione> findAllByFine (Date d);
	List<Prenotazione> findAllByStato (State s);
}
