package com.group.projectwork.presentation;

import java.io.IOException;

import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.repository.VeicoloDB;
import com.group.projectwork.service.FileSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class VeicoloCTRL {
    
    @Autowired
	FileSRV fs;

    @Autowired
	VeicoloSRV vsrv;

    @PostMapping("/save")
	public String saveVeicolo(Model model, Veicolo veicoloForm,
			@RequestParam(name = "image") MultipartFile immagine) {

		if (immagine != null) {
			try {
				String percorso = fs.saveFile("img/veicoli", veicoloForm.getDescrizione() + immagine.getName(),
						immagine);
				veicoloForm.setImmagine(percorso);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		vsrv.addVeicolo(veicoloForm);
		return "redirect:/";
	}

}
