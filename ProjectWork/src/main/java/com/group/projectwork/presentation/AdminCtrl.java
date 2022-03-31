package com.group.projectwork.presentation;

import static com.group.projectwork.utility.ErrorUtils.*;

import javax.servlet.http.HttpSession;

import com.group.projectwork.dto.LoginDTO;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.service.AlimentazioneSRV;
import com.group.projectwork.service.CategoriaSRV;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("utente")
public class AdminCtrl {
	
	@Autowired
    UtenteSRV usrv;
	
	@Autowired
    VeicoloSRV vSrv;
	
	@Autowired
	CategoriaSRV catSrv;
	
	@Autowired
	AlimentazioneSRV aliSrv;
	
    @GetMapping("/view")
	public String loginForm(Utente user, Model model) {
    	
    	if(user.getRuolo()!= Role.RUOLO_ADMIN)
    		return accessDeniedMVC(model);
		model.addAttribute("categorie",this.catSrv.getAll());
		model.addAttribute("alimentazioni",this.aliSrv.getAll());
    	model.addAttribute("veicoli",this.vSrv.getAll());
		return "view";
    }
	
	@GetMapping("/pannello")
	public String toPannello(Utente u, Model model){
		if(u.getRuolo()!=Role.RUOLO_ADMIN)
			accessDeniedMVC(model);

		return "pannello";
	}  
}
