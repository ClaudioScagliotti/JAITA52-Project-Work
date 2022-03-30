package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.group.projectwork.dto.UpdatePrenotazioneDTO;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.PrenotazioneException;
import com.group.projectwork.exception.VeicoloNotFoundException;
import com.group.projectwork.presentation.PrenotazioneCtrl;
import com.group.projectwork.repository.PrenotazioneDB;
import com.group.projectwork.repository.TokenDB;
import com.group.projectwork.repository.UtenteDB;
import com.group.projectwork.service.PrenotazioneSRV;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.UtenteSRV;
import com.group.projectwork.utility.DateUtils;
import com.mysql.cj.protocol.x.Ok;

@SpringBootTest
class PrenotazioneTest {

	@Autowired
	PrenotazioneDB repo;
	
	@Autowired
	UtenteSRV uSrv;

	@Autowired
	PrenotazioneCtrl ctrl;
	
	@Autowired
	PrenotazioneSRV pSrv;
	

	@Test
	@Transactional
	void testingPrenotazioneRepo() {
		Date d1 = DateUtils.parseDate("2022-04-30");
		Date d2 = DateUtils.parseDate("2022-03-23");
		System.out.println(d1);
		Utente u= uSrv.getById(1);
		State state = State.Annullato;
		assertEquals(2,repo.findAllByFine(d1).size());
		assertEquals(2,repo.findAllByInizio(d2).size());
		assertEquals(2,repo.findAllByUtente(u).size());
		assertEquals(2,repo.findAllByStato(state).size());
	}
	
	@Test
	@Transactional
	void testingPrenotazioniAttiveByVeicolo() {
		var pre = this.repo.findPrenotazioniAttive(6);
		assertEquals(1,pre.size());
	}
	
	@Test
	@Transactional
	void testingPrenotazioneSRV() {
		Prenotazione p= pSrv.getById(1);
		UpdatePrenotazioneDTO dto= new UpdatePrenotazioneDTO();
		Utente u= uSrv.getById(2);
		SimpleDateFormat d2= new SimpleDateFormat("2022-04-02");
		Date today= new Date();
		dto.setId(p.getId());
		dto.setInizio(p.getInizio());
		dto.setFine(today);
		dto.setvId(9);
		try {
			pSrv.updPrenotazione(dto, u);
		} catch (AccessDeniedException e) {
			fail();
		} catch (PrenotazioneException e) {
			fail(); //fallisce qui ma è corretto che fallisca
		} catch (VeicoloNotFoundException e) {
			fail();
		}
		
		//var pre = this.repo.findPrenotazioniAttive(9);
		//assertEquals(1, pre.size());
	}
	
	
}
