package com.group.projectwork.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;

public interface PrenotazioneDB extends JpaRepository<Prenotazione, Integer> {

	List<Prenotazione> findAllByUtente (Utente u);
	List<Prenotazione> findAllByInizio (Date model);
	List<Prenotazione> findAllByFine (Date model);
	List<Prenotazione> findAllByStato (State model);
	
	
}
