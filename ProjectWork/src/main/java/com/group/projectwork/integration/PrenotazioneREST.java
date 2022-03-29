package com.group.projectwork.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.projectwork.dto.PrenotazioneDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.TokenExpiredException;
import com.group.projectwork.exception.VeicoloNotFoundException;
import com.group.projectwork.service.PrenotazioneSRV;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;

@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneREST {

	@Autowired
	PrenotazioneSRV srv;
	
	@Autowired
	TokenSRV tokenSrv;
	
	@Autowired
	UtenteSRV usrv;
	
	@PostMapping
	public ResponseEntity<Prenotazione> addPrenotazione(
			@RequestBody() PrenotazioneDTO dto,
			@RequestParam(name = "token") String token) {
		
		try {
			var user = this.tokenSrv.getUtente(token);
			var saved = this.srv.addPrenotazione(dto, user);
			return ResponseEntity.ok(saved);
		}catch (TokenExpiredException|AccessDeniedException|VeicoloNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
