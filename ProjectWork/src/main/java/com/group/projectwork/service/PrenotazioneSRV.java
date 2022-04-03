package com.group.projectwork.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.projectwork.dto.PrenotazioneDTO;
import com.group.projectwork.dto.UpdatePrenotazioneDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.PrenotazioneException;
import com.group.projectwork.exception.VeicoloNotFoundException;
import com.group.projectwork.repository.PrenotazioneDB;

@Service
public class PrenotazioneSRV {

	@Autowired
	VeicoloSRV vSrv;

	@Autowired
	PrenotazioneDB pdb;

	public List<Prenotazione> getAll() {
		return this.pdb.findAll();
	}

	public Prenotazione addPrenotazione(Prenotazione p) {
		return this.pdb.save(p);
	}

	public Prenotazione addPrenotazione(PrenotazioneDTO dto, Utente loggedIn)
			throws AccessDeniedException, VeicoloNotFoundException {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
			throw new AccessDeniedException();

		Veicolo v = vSrv.getVeicoloById(dto.getvId());

		if (v == null || !v.getDisponibile())
			throw new VeicoloNotFoundException();

		var prenotazioni = this.pdb.findPrenotazioniAttive(v.getId());
		// controlla che non ci siano altre prenotazioni attive per
		// il veicolo nella data selezionata
		if (prenotazioni.stream().anyMatch(
				p -> p.getFine().compareTo(dto.getInizio()) > 0 && p.getInizio().compareTo(dto.getFine()) < 0))
			throw new VeicoloNotFoundException();

		Prenotazione pr = new Prenotazione();
		pr.setInizio(dto.getInizio());
		pr.setFine(dto.getFine());
		pr.setVeicolo(v);
		pr.setUtente(loggedIn);
		pr.setStato(State.Prenotato);

		var prenotazione = this.addPrenotazione(pr);

		prenotazione.getVeicolo().setDisponibile(false);
		this.vSrv.save(v);

		return prenotazione;
    }
    
	public Prenotazione updPrenotazione(UpdatePrenotazioneDTO dto, Utente loggedIn) throws AccessDeniedException, VeicoloNotFoundException, PrenotazioneException 
	{	
    	if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
    		throw new AccessDeniedException();
    	if((dto.getFine().getTime()-dto.getInizio().getTime())/(60000)<15)
    		throw new PrenotazioneException("Durata non valida");
    	if(dto.getFine().compareTo(new Date())<=0) {
    		throw new PrenotazioneException("Prenotazione terminata");
    	}
    	
    	Veicolo newV = vSrv.getVeicoloById(dto.getvId());
		
		Prenotazione oldP= this.getById(dto.getId());
		
		//veicolo cambia?
		if(newV == null)
			throw new VeicoloNotFoundException();
		else if(newV.getId()!=oldP.getVeicolo().getId()) {
			
			if (!newV.getDisponibile())
				throw new VeicoloNotFoundException();
			
			var prenotazioni = this.pdb.findPrenotazioniAttive(newV.getId());
			//controlla che non ci siano altre prenotazioni attive per
			//il veicolo nella data selezionata
			if(prenotazioni.stream()
					.filter(p-> p.getId()!=dto.getId())
					.anyMatch(p->
						p.getFine().compareTo(dto.getInizio())>0 &&
						p.getInizio().compareTo(dto.getFine())<0			
					))
				throw new VeicoloNotFoundException();
			
			this.vSrv.setDisp(oldP.getVeicolo(), true);
			this.vSrv.setDisp(newV, false);
			oldP.setVeicolo(newV);
		}
		
		oldP.setInizio(dto.getInizio());
		oldP.setFine(dto.getFine());

		return this.pdb.save(oldP);
    }
    
	private boolean isRunning(Prenotazione p) {
		return p.getStato() == State.Prenotato || p.getStato() == State.Corrente;
	}

	public void delPrenotazioneById(int id, Utente loggedIn) throws AccessDeniedException {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			throw new AccessDeniedException();

		Prenotazione p = getById(id);

		if (p.getFine().after(new Date()))
			if (this.isRunning(p))
				p.getVeicolo().setDisponibile(true);

		this.pdb.delete(p);
	}

	public Prenotazione terminaPrenotazione(int id, Utente loggedIn)
			throws AccessDeniedException, PrenotazioneException {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
			throw new AccessDeniedException();

		Prenotazione p = getById(id);
		if (loggedIn.getId() != p.getUtente().getId())
			throw new AccessDeniedException();
		else if (!this.isRunning(p)) 
			throw new PrenotazioneException("Prenotazione non attiva");
			
		Date date = new Date();
		if (p.getInizio().after(date)) {
			p.setStato(State.Annullato);
		} else if (p.getFine().after(date)) {
			p.setFine(date);
			p.setStato(State.Completato);
		} else {
			p.setStato(State.Completato);			
			this.pdb.save(p);
			throw new PrenotazioneException("Prenotazione passata");
		}

		p.getVeicolo().setDisponibile(true);
		return this.pdb.save(p);
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
