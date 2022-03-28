package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.group.projectwork.entity.Prenotazione.State;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.repository.PrenotazioneDB;
import com.group.projectwork.repository.TokenDB;
import com.group.projectwork.repository.UtenteDB;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.utility.DateUtils;

@SpringBootTest
class PrenotazioneTest {

	@Autowired
	PrenotazioneDB repo;
	
	@Autowired
	UtenteDB udb;
	

	
	@Test
	@Transactional
	void testingPrenotazioneRepo() {
		Date d1 = DateUtils.parseDate("2022-04-30");
		Date d2 = DateUtils.parseDate("2022-03-23");
		System.out.println(d1);
		Utente u= udb.getById(1);
		State model=State.Annullato;
		assertEquals(2,repo.findAllByFine(d1).size());
		assertEquals(1,repo.findAllByInizio(d2).size());
		assertEquals(2,repo.findAllByUtente(u).size());
		assertEquals(2,repo.findAllByStato(model).size());
		
		
		
		

		/*var opt = repo.findById(1);
		if(opt.isEmpty())
			fail();
		
		var paoloRossi = opt.get();
		assertEquals(Role.RUOLO_ADMIN,paoloRossi.getRuolo());
		assertEquals(DateUtils.parseDate("1994-6-7"),paoloRossi.getDataNascita());
		*/
	}
	

}
