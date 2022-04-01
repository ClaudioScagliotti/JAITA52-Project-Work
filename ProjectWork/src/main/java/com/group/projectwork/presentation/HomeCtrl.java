package com.group.projectwork.presentation;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import static com.group.projectwork.utility.SessionUtils.*;

import java.util.List;

import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.service.CategoriaSRV;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeCtrl {

	@Autowired
	TokenSRV tSrv;

	@Autowired
	UtenteSRV uSrv;

	@Autowired
	VeicoloSRV vSrv;
	
	@Autowired
	CategoriaSRV cSrv;

	@GetMapping(value = { "", "/index" })
	public String toIndex(Model model, HttpSession session) {
		
		sessionCleanup(session);

		model.addAttribute("disponibili", this.vSrv.getByDisponibilita(true));
		return "index";
	}
	
	@GetMapping("disponibilita")
	public String toDisponibilita(Model model, HttpSession session,
			@RequestParam(required = false, name = "categoria") Integer catId) {
		
		sessionCleanup(session);

		List<Veicolo> list;
		
		try {
			if(catId == null)
				throw new EntityNotFoundException();
			
			var cat = this.cSrv.getById(catId);
			model.addAttribute("categoria",cat);
			list = this.vSrv.getAllByCategoria(cat);			
		}catch(Exception ex) {
			list = this.vSrv.getAll();
		}
		
		model.addAttribute("veicoli", list);
		return "disponibilita";
	}
}
