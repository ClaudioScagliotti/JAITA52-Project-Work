package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.repository.UtenteDB;
import com.group.projectwork.utility.DateUtils;

@SpringBootTest
class UtenteTest {

	@Autowired
	UtenteDB repo;
	
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
	
}
