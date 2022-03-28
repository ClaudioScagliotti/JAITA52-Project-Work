package com.group.projectwork.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.service.PrenotazioneSRV;

@RestController
public class PrenotazioneREST {

	@Autowired
	PrenotazioneSRV srv;
	
	
	
}
