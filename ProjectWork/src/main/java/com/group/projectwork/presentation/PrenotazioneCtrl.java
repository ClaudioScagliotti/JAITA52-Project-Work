package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.PrenotazioneDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.service.PrenotazioneSRV;
import com.group.projectwork.service.VeicoloSRV;

@Controller
@SessionAttributes("utente")
public class PrenotazioneCtrl {
	
	@Autowired
	PrenotazioneSRV srv;
	
	@Autowired
	VeicoloSRV vSrv;

	@PostMapping("/add")
	public String addVeicolo( Utente loggedIn, Model model, PrenotazioneDTO p) {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE))
			return accessDeniedMVC(model);
		
		
		Veicolo v= vSrv.getVeicoloById(p.getvId());
		
		if(v==null || !v.getDisponibilita())
				return genericErrorMVC(model, "Veicolo non disponibile");
		
			Prenotazione pr= new Prenotazione();
			pr.setInizio(p.getInizio());
			pr.setFine(p.getFine());
			pr.setVeicolo(v);
			pr.setUtente(loggedIn);
			pr.setStato(State.Prenotato);
			
			this.srv.addPrenotazione(pr);
			model.addAttribute("prenotazione", p);
			return "utente";

	}
}
