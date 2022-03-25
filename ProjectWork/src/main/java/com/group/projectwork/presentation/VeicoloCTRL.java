package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;

import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.service.FileSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

public class VeicoloCTRL {
    
    @Autowired
	FileSRV fs;

    @Autowired
	VeicoloSRV vsrv;

    @PostMapping("/save")
	public String saveVeicolo(
			@SessionAttribute(name = "utente", required = true) Utente loggedIn,
			Model model, Veicolo veicoloForm,
			@RequestParam(name = "image") MultipartFile immagine) {

    	if(!loggedIn.getRuolo().equals(Role.RUOLO_ADMIN))
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
