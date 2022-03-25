package com.group.projectwork.integration;

import com.group.projectwork.dto.LoginDTO;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utente")
public class UtenteREST {
    @Autowired 
     UtenteSRV usrv;
    
    @Autowired
    TokenSRV tokenSrv;

	@PostMapping
    public ResponseEntity<String> login(@RequestBody() LoginDTO data){
        var email= data.getEmail();
        var utente= this.usrv.getByEmail(email);
        if(utente!= null && utente.getPassword().equals(data.getPassword())){
           return ResponseEntity.ok( this.tokenSrv.generate(utente).getValore());
        }
        return ResponseEntity.badRequest().build();
    }

	@GetMapping
	public ResponseEntity<String> logout(@RequestParam("token") String token){
		if(this.tokenSrv.delete(token))			
			return ResponseEntity.ok("see you again!");
		return ResponseEntity.badRequest().build();
	}
    
}
