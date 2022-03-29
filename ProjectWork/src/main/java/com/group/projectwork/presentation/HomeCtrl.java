package com.group.projectwork.presentation;

import javax.servlet.http.HttpSession;

import com.group.projectwork.dto.LoginDTO;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeCtrl {

	@Autowired
	TokenSRV tSrv;
	
	@Autowired
    UtenteSRV uSrv;
	
	@Autowired
	VeicoloSRV vSrv;

	@GetMapping(value={"","/index"})
	public String toIndex(Model model){
		model.addAttribute("disponibili", this.vSrv.getByDisponibilita(true));
		return "index";
	}    
}
