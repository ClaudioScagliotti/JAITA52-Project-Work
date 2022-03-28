package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.accessDeniedMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.service.PrenotazioneSRV;

@Controller
@SessionAttributes("utente")
public class PrenotazioneCtrl {
	
	@Autowired
	PrenotazioneSRV srv;

	@PostMapping("/add")
	public String addVeicolo( Utente loggedIn, Model model, Prenotazione p) {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_UTENTE) || !p.getVeicolo().getDisponibilita())
			accessDeniedMVC(model);
		
		try {
			this.srv.addPrenotazione(p);
			model.addAttribute("prenotazione", p);
			return "utente.html";
		} catch (Exception e) {
			// TODO ERROR
			e.printStackTrace();
			
		}
		return "redirect:/";
	}
}
