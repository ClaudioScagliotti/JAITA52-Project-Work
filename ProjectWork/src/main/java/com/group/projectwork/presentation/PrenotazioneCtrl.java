package com.group.projectwork.presentation;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.service.PrenotazioneSRV;

@Controller
@RequestMapping("/prenotazione")
@SessionAttributes("utente")
public class PrenotazioneCtrl {
	
	@Autowired
	PrenotazioneSRV srv;

	@GetMapping
	public String get(Utente utente, Model model){
		if (utente.getEmail()==null) {
			model.addAttribute("redirectTo","/prenotazione");
			return "redirect:/login-page";
		}
		
		var total = srv.getByUtente(utente);
		List<Prenotazione> attive = new LinkedList<>();
		List<Prenotazione> concluse = new LinkedList<>();
		total.stream().forEach(p->{
			if(p.getStato()==State.Corrente || p.getStato()==State.Prenotato)
				attive.add(p);
			else
				concluse.add(p);
		});
		model.addAttribute("attive", attive);
		model.addAttribute("concluse", concluse);
		return "prenotazioni";
	}
}
