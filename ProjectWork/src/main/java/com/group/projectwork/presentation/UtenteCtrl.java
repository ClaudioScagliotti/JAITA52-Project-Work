package com.group.projectwork.presentation;

import javax.servlet.http.HttpSession;

import com.group.projectwork.dto.LoginDTO;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.service.PrenotazioneSRV;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.group.projectwork.exception.AccessDeniedException;

@Controller
public class UtenteCtrl {

	@Autowired
	TokenSRV tSrv;
	
	@Autowired
    UtenteSRV usrv;

    @Autowired
    PrenotazioneSRV pSrv;
	
    @GetMapping("/login-page")
	public String loginForm(Model model, HttpSession session) {
		
		if(session.getAttribute("utenteLoggato") == null) {
            model.addAttribute("loginData", new LoginDTO());
            return "login";
        }
		else return "homepage";
    }

    @PostMapping("/login")
    public String executeLogin(LoginDTO data, Model model, HttpSession session,
    		@RequestParam(name = "redirect",required = false, defaultValue = "") String redirect){
        var email= data.getEmail();
        var utente= this.usrv.getByEmail(email);
        if(utente!= null && utente.getPassword().equals(data.getPassword())){
            session.setAttribute("utente", utente);
            this.tSrv.generate(utente);
            
            if(redirect!=null && !redirect.isBlank()) {
            	return "redirect:"+redirect;
            }
            return "redirect:index";
        }
        return "error";
    }




    @GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.setAttribute("utente", null);
		return "redirect:index";
	}

}
