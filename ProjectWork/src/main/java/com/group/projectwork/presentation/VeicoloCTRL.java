package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;

import java.util.Optional;

import javax.websocket.server.PathParam;

import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.ImageSaveException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller("/veicolo")
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

	@GetMapping("/edit/{id}")
	public String editVeicolo(@SessionAttribute(name = "utente", required = true) Utente loggedIn,
			@PathParam("id") int id, Model model) {

		if (loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			accessDeniedMVC(model);

		var selected = this.vsrv.getVeicoloById(id);
		if (selected != null) {
			model.addAttribute("edit", true);
			model.addAttribute("categorie",this.catSrv.getAll());
			model.addAttribute("alimentazioni",this.aliSrv.getAll());
			model.addAttribute("veicolo", factory.createDto(selected));
			return "test/save-veicolo.html";
		}

		return "redirect:/";
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

			}
		} else if (page.isPresent()) {
			// TODO
		} else if (number.isPresent()) {
			// TODO
		}

		return "redirect:/index.html";
	}

	@PostMapping("/save")
	public String saveVeicolo(@SessionAttribute(name = "utente", required = true) Utente loggedIn, Model model,
			Veicolo veicoloForm, @RequestParam(name = "image") MultipartFile immagine) {

		if (!loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
			accessDeniedMVC(model);

		try {
			vsrv.saveVeicolo(veicoloForm, immagine);
		} catch (ImageSaveException e) {
			// TODO ERROR
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
