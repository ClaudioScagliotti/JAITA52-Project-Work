package com.group.projectwork.presentation;

import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;
import com.group.projectwork.service.VeicoloSRV;
import com.group.projectwork.utility.ErrorUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("utente")
public class AdminCtrl {

	@Autowired
	TokenSRV tSrv;
	
	@Autowired
    UtenteSRV uSrv;
	
	@Autowired
	VeicoloSRV vSrv;

	@GetMapping("pannello")
	public String toPannello(Utente u, Model model){
		if(u.getRuolo()!=Role.RUOLO_ADMIN)
			ErrorUtils.accessDeniedMVC(model);
		return "pannello";
	}  
}
