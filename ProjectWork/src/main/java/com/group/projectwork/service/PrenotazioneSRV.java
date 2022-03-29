package com.group.projectwork.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import com.group.projectwork.dto.PrenotazioneDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.VeicoloNotFoundException;
import com.group.projectwork.repository.PrenotazioneDB;

@Service
public class PrenotazioneSRV {

	@Autowired
	VeicoloSRV vSrv;
	
	@Autowired
	PrenotazioneDB pdb;
	
	public List<Prenotazione> getAll(){
		return this.pdb.findAll();
	}
		
    public Prenotazione addPrenotazione(Prenotazione p){
    	return this.pdb.save(p);
    }
    
    public Prenotazione addPrenotazione(PrenotazioneDTO dto, Utente loggedIn) throws AccessDeniedException, VeicoloNotFoundException{
		
    	if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
			throw new AccessDeniedException();

		Veicolo v = vSrv.getVeicoloById(dto.getvId());

		if (v == null || !v.getDisponibilita())
			throw new VeicoloNotFoundException();

		var prenotazioni = this.pdb.findPrenotazioniAttive(v.getId());
		//controlla che non ci siano altre prenotazioni attive per
		//il veicolo nella data selezionata
		if(prenotazioni.stream().anyMatch(p->
			p.getFine().compareTo(dto.getInizio())>0 &&
			p.getInizio().compareTo(dto.getFine())<0			
			))
			throw new VeicoloNotFoundException();
		
		Prenotazione pr = new Prenotazione();
		pr.setInizio(dto.getInizio());
		pr.setFine(dto.getFine());
		pr.setVeicolo(v);
		pr.setUtente(loggedIn);
		pr.setStato(State.Prenotato);

		var prenotazione =  this.addPrenotazione(pr);
		
		prenotazione.getVeicolo().setDisponibilita(false);
		this.vSrv.save(v);
		
		return prenotazione;
    }
    
    public void delPrenotazioneById(int id, Utente loggedIn)throws AccessDeniedException{
    	
    	if (!loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			throw new AccessDeniedException();
    	
    	this.pdb.deleteById(id);
    }
    
    public Prenotazione terminaPrenotazione(int id, Utente loggedIn) throws AccessDeniedException{
    	
    	Prenotazione p= getById(id);
    	if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
			throw new AccessDeniedException();
    	  
    	   Date date = new Date();    
    	   System.out.println(date);
    	if(p.getInizio().before(date)) {
    		p.setStato(State.Annullato);
    	}else {
    	p.setStato(State.Completato);
    	p.setFine(date);
    	}
    	p.getVeicolo().setDisponibilita(true);
    	this.pdb.save(p);
    	return p;
    		
    }
    
    

	public Prenotazione getById(int id) {
		var opt = pdb.findById(id);
		if (opt.isPresent())
			return opt.get();
		return null;
	}

	public List<Prenotazione> getListaPrenotazioni() {
		return this.pdb.findAll();
	}

	public List<Prenotazione> getByStato(State s) {
		return this.pdb.findAllByStato(s);
	}

	public List<Prenotazione> getByInizio(Date d) {
		return this.pdb.findAllByInizio(d);
	}

	public List<Prenotazione> getByFine(Date d) {
		return this.pdb.findAllByFine(d);
	}

	public List<Prenotazione> getByUtente(Utente u) {
		return this.pdb.findAllByUtente(u);
	}
	
	public List<Prenotazione> getByVeicolo(Veicolo v) {
		return this.pdb.findAllByVeicolo(v);
	}
}
