package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Prenotazione;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.exception.AccessDeniedException;
import com.group.projectwork.exception.PrenotazioneException;
import com.group.projectwork.presentation.PrenotazioneCtrl;
import com.group.projectwork.repository.PrenotazioneDB;
import com.group.projectwork.service.PrenotazioneSRV;
import com.group.projectwork.service.UtenteSRV;
import com.group.projectwork.utility.DateUtils;

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
	void testingTerminaPrenotazioniService() {
		Utente u = uSrv.getById(2);
		try {
			pSrv.terminaPrenotazione(32, u);
			fail();
		} catch (PrenotazioneException e) {
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
		try {
			var p = pSrv.getById(35);
			var oldDate = p.getFine().getTime();
			var pAfter = pSrv.terminaPrenotazione(35, u);
			var timeAfter = pAfter.getFine().getTime();
			assertNotEquals(oldDate,
					timeAfter);
			Date currDate = new Date();
			assertEquals(timeAfter, currDate.getTime(),10000.0);
			assertEquals(Prenotazione.State.Completato, pAfter.getStato());
		} catch (Exception e) {
			fail();
		}
	}
	
}
