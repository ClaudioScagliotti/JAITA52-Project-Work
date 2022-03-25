package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.repository.TokenDB;
import com.group.projectwork.repository.UtenteDB;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.utility.DateUtils;

@SpringBootTest
class UtenteTest {

	@Autowired
	UtenteDB repo;
	
	@Autowired
	TokenSRV tokenSrv;
	@Autowired
	TokenDB tokenDB;
	
	@Test
	@Transactional
	void testingUtenteRepo() {
	
		assertEquals(2,repo.findAll().size());

		var opt = repo.findById(1);
		if(opt.isEmpty())
			fail();
		
		var paoloRossi = opt.get();
		assertEquals(Role.RUOLO_ADMIN,paoloRossi.getRuolo());
		assertEquals(DateUtils.parseDate("1994-6-7"),paoloRossi.getDataNascita());
	}
	
	@Test
	@Transactional
	void testingToken() {
		//prendo il primo utente nel DB per usarlo come soggetto di test
		var testUser = repo.findAll().get(0);
		//genero il token per l'utente
		var token = this.tokenSrv.generate(testUser);
		//memorizzo la data di creazione inserita del srv
		var creationDate = token.getIns();
		//controllo che il token sia stato effettivamente creato per l'utente correto
		assertEquals(testUser.getId(),token.getUtente().getId());
		//effettuo un check sul token che dovrebbe resettare ins al tempo del check
		this.tokenSrv.isValid(token.getValore());
		//prendo nuovamente il token
		var afterUpd = this.tokenDB.findById(token.getId()).get();
		//controllo che i due token siano gli stessi
		assertEquals(token.getUtente().getId(),afterUpd.getUtente().getId());
		assertEquals(token.getValore(),afterUpd.getValore());
		//controllo che i due tempi differiscono
		assertNotEquals(creationDate,afterUpd.getIns());
	}
}
