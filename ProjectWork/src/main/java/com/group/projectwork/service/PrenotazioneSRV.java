package com.group.projectwork.service;

import static com.group.projectwork.utility.ErrorUtils.accessDeniedMVC;
import static com.group.projectwork.utility.ErrorUtils.genericErrorMVC;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    public Prenotazione addPrenotazione(PrenotazioneDTO p, Utente loggedIn) throws AccessDeniedException, VeicoloNotFoundException{
		
    	if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
			throw new AccessDeniedException();

		Veicolo v = vSrv.getVeicoloById(p.getvId());

		if (v == null || !v.getDisponibilita())
			throw new VeicoloNotFoundException();

		Prenotazione pr = new Prenotazione();
		pr.setInizio(p.getInizio());
		pr.setFine(p.getFine());
		pr.setVeicolo(v);
		pr.setUtente(loggedIn);
		pr.setStato(State.Prenotato);

		return this.addPrenotazione(pr);
    }
    
    public void delPrenotazioneById(int id){
    	this.pdb.deleteById(id);
    }
    
    
    public Prenotazione getById(int id) {
   	 var opt = pdb.findById(id);
   	 if(opt.isPresent())
   		 return opt.get();
   	 return null;
     }
    
    public List<Prenotazione> getByStato(State s){
    	return this.pdb.findAllByStato(s);
    }
    
    public List<Prenotazione> getByInizio(Date d){
    	return this.pdb.findAllByInizio(d);
    }
    
    public List<Prenotazione> getByFine(Date d){
    	return this.pdb.findAllByFine(d);
    }
    
    public List<Prenotazione> getByUtente(Utente u){
    	return this.pdb.findAllByUtente(u);
    }
    

	List<Prenotazione> getAll() {
		return this.pdb.findAll();
	}

	public Prenotazione addPrenotazione(Prenotazione p) {
		return this.pdb.save(p);
	}

	public void delPrenotazioneById(int id) {
		this.pdb.deleteById(id);
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
}
