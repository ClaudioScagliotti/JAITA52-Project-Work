package com.group.projectwork.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.projectwork.entity.Utente;
import com.group.projectwork.service.PrenotazioneSRV;

@Controller
@RequestMapping("/lista-prenotazioni")
@SessionAttributes("utente")
public class PrenotazioneCtrl {
	
	@Autowired
	PrenotazioneSRV srv;

	@GetMapping
	public String get(Utente utente, Model model){
		if (utente.getEmail()==null) {
			return "redirect:/login-page";
		}
		model.addAttribute("prenotazioni", srv.getListaPrenotazioni());

		return "prenotazioni";
	}
	
}
