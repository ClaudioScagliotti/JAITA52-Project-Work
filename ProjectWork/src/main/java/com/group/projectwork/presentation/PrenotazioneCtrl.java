package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;
import java.util.LinkedList;
import java.util.List;

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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.service.PrenotazioneSRV;
import com.group.projectwork.service.VeicoloSRV;

@Controller
@RequestMapping("/prenotazione")
@SessionAttributes("utente")
public class PrenotazioneCtrl {
	
	@Autowired
	PrenotazioneSRV srv;
	
	@Autowired
	VeicoloSRV vSrv;

	@PostMapping("/add")
	public String addPrenotazione( Utente loggedIn, Model model, PrenotazioneDTO dto) {

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
	
	@PostMapping("/termina")
	public String terPrenotazione( Utente loggedIn, Model model, int id) {

		try {
			var p = this.srv.terminaPrenotazione(id, loggedIn);
			model.addAttribute("prenotazione", p);
			return "utente";
		}catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		}
	}
	
	@PostMapping("/del")
	public String delPrenotazione(Utente loggedIn, Model model, int id) {
		
		try {
			this.srv.delPrenotazioneById(id, loggedIn);
			return "utente";
		}catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		}
	}
}
