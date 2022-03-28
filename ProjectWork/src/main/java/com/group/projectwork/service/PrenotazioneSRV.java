package com.group.projectwork.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.repository.PrenotazioneDB;
@Service
public class PrenotazioneSRV {

	@Autowired
	PrenotazioneDB pdb;
	
	List<Prenotazione> getAll(){
		return this.pdb.findAll();
	}
		
    public Prenotazione addPrenotazione(Prenotazione p){
    	return this.pdb.save(p);
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
    
    List<Prenotazione> getByStato(State s){
    	return this.pdb.findAllByStato(s);
    }
    
    List<Prenotazione> getByInizio(Date d){
    	return this.pdb.findAllByInizio(d);
    }
    
    List<Prenotazione> getByFine(Date d){
    	return this.pdb.findAllByFine(d);
    }
    
    List<Prenotazione> getByUtente(Utente u){
    	return this.pdb.findAllByUtente(u);
    }
    
    
	
}
