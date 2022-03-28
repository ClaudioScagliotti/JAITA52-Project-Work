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
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloNotFoundException;
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
	public String addVeicolo( Utente loggedIn, Model model, PrenotazioneDTO dto) {

		try {
			var p = this.srv.addPrenotazione(dto, loggedIn);
			model.addAttribute("prenotazione", p);
			return "utente";
		}catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		}catch (VeicoloNotFoundException e) {
			return genericErrorMVC(model, "Veicolo non disponibile");
		}
	}
}
