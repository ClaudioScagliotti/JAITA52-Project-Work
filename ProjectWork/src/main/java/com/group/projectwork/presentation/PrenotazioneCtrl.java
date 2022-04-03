package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group.projectwork.dto.PrenotazioneDTO;
import com.group.projectwork.dto.UpdatePrenotazioneDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.PrenotazioneException;
import com.group.projectwork.exception.VeicoloNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	public String addPrenotazione(
			@SessionAttribute(name="utente") Utente loggedIn,
			Model model, PrenotazioneDTO dto) {

		try {
			var p = this.srv.addPrenotazione(dto, loggedIn);
			model.addAttribute("prenotazione", p);
			return "redirect:/prenotazione";
		}catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		}catch (VeicoloNotFoundException e) {
			return genericErrorMVC(model, "Veicolo non disponibile");
		}
	}

	@GetMapping
	public String get(
			@SessionAttribute(name="utente") Utente utente, Model model){
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
		return "utente";
	}
	
	@PostMapping("/termina")
	public String terPrenotazione(
			@SessionAttribute(name="utente") Utente loggedIn,
			Model model, @RequestParam(name = "id") int id) {

		try {
			var p = this.srv.terminaPrenotazione(id, loggedIn);
			model.addAttribute("prenotazione", p);
			return "redirect:/prenotazione";
		} catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		} catch (PrenotazioneException e) {
			return genericErrorMVC(model,e.getMessage());
		}
	}
	
	@PostMapping("/del")
	public String delPrenotazione(
			@SessionAttribute(name="utente") Utente loggedIn,
			Model model, @RequestParam(name = "id") int id) {
		
		try {
			this.srv.delPrenotazioneById(id, loggedIn);
			return "redirect:/prenotazione";
		}catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		}
	}
	
	@PostMapping("/upd")
	public String updPrenotazione(
			@SessionAttribute(name="utente") Utente loggedIn, Model model,
			UpdatePrenotazioneDTO dto) {
		
		try {
			this.srv.updPrenotazione(dto, loggedIn);
			return "redirect:/prenotazione";
		} catch (AccessDeniedException e) {
			return accessDeniedMVC(model);
		} catch (VeicoloNotFoundException e) {
			return genericErrorMVC(model, "Veicolo non disponibile");
		} catch (PrenotazioneException e) {
			return genericErrorMVC(model, e.getMessage());
		}
	}
}
