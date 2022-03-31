package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;

import java.util.Optional;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.UpdateVeicoloDTO;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.factory.VeicoloFactory;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.service.AlimentazioneSRV;
import com.group.projectwork.service.CategoriaSRV;
import com.group.projectwork.service.FileSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/veicolo")
public class VeicoloCTRL {

	@Autowired
	FileSRV fs;

	@Autowired
	VeicoloSRV vsrv;
	
	@Autowired
	CategoriaSRV catSrv;
	
	@Autowired
	AlimentazioneSRV aliSrv;

	@Autowired
	VeicoloFactory factory;

	private String toEditForm(Model model, CreateVeicoloDTO dto) {
		model.addAttribute("edit", dto instanceof UpdateVeicoloDTO );
		model.addAttribute("categorie",this.catSrv.getAll());
		model.addAttribute("alimentazioni",this.aliSrv.getAll());
		model.addAttribute("veicolo", dto);
		return "inserisci";
	}
	
	@GetMapping("/edit/{id}")
	public String editVeicolo(@SessionAttribute(name = "utente", required = true) Utente loggedIn,
			@PathVariable("id") int id, Model model) {

		if (loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			accessDeniedMVC(model);

		var selected = this.vsrv.getVeicoloById(id);
		if (selected != null)
			return this.toEditForm(model, factory.createDto(selected));

		return "redire/ct:/";
	}
	
	@GetMapping("/add-form")
	public String preAddVeicolo(
			@SessionAttribute(name = "utente", required = true) Utente loggedIn,
			Model model) {

		if (loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			accessDeniedMVC(model);

		return this.toEditForm(model, new CreateVeicoloDTO());
	}
	
	@GetMapping
	public String getVeicolo(@SessionAttribute(name = "utente", required = false) Optional<Utente> optLoggedIn,
			@RequestParam(name = "id", required = false) Optional<Integer> id,
			// page id
			@RequestParam(name = "pid", required = false) Optional<Integer> page,
			// number x page
			@RequestParam(name = "nxp", required = false) Optional<Integer> number, Model model) {

		if (id.isPresent()) {
			var selected = this.vsrv.getVeicoloById(id.get());
			if (selected != null) {
				model.addAttribute("veicolo", selected);
				return "veicolo";
			}
		} else if (page.isPresent()) {
			// TODO
		} else if (number.isPresent()) {
			// TODO
		}

		return "redirect:/index.html";
	}

	@PostMapping("/add")
	public String addVeicolo(@SessionAttribute(name = "utente", required = true) Utente loggedIn, Model model,
			CreateVeicoloDTO dto) {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			accessDeniedMVC(model);

		try {
			this.vsrv.addVeicolo(dto);
			return "index.html";
		} catch (ImageSaveException e) {
			// TODO ERROR
			e.printStackTrace();
		} catch (VeicoloParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/upd")
	public String updateVeicolo(@SessionAttribute(name = "utente", required = true) Utente loggedIn, Model model,
			UpdateVeicoloDTO dto) {
		
		if (!loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			accessDeniedMVC(model);
		
		try {
			this.vsrv.updVeicolo(dto);
			return "redirect:/view";
		} catch (ImageSaveException e) {
			// TODO ERROR
			e.printStackTrace();
		} catch (VeicoloParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
