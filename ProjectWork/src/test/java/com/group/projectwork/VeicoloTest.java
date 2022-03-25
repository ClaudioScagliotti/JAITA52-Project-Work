package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.group.projectwork.entity.Alimentazione;
import com.group.projectwork.entity.Categoria;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.repository.AlimentazioneDB;
import com.group.projectwork.repository.CategoriaDB;
import com.group.projectwork.repository.VeicoloDB;
import com.group.projectwork.service.VeicoloSRV;

@SpringBootTest
class VeicoloTest {

	@Autowired
	VeicoloDB repo;

	@Autowired
	AlimentazioneDB ali_db;

	@Autowired
	CategoriaDB cat_db;

	@Autowired
	VeicoloSRV vsrv;

	
	@Test
	@Transactional
	void testingVeicoloAliAndCatRepo() {
	
		assertEquals(3,repo.findAll().size());
		assertEquals(5,ali_db.findAll().size());
		assertEquals(3,cat_db.findAll().size());

		var opt = repo.findAllByMarca("Fiat");
		if(opt.isEmpty())
			fail();
		
		var a_opt = ali_db.findById(1);
		if(a_opt.isEmpty())
			fail();
		
		var aliId1 = a_opt.get();
		
		var fiatPanda = opt.get(0);
		assertEquals("Fiat",fiatPanda.getMarca());
		assertEquals(aliId1,fiatPanda.getAlimentazione());
		
		assertEquals(2, this.repo.findAllByAlimentazione(aliId1).size());
	}

	@Test
	@Transactional
	void testAliGetId() {
		var ali = ali_db.findById(1);
		assertNotEquals(null,ali.get());
		assertEquals("elettrica",ali.get().getNome());
	}
	
	@Test
	@Transactional
	void testingVeicolo() {
		assertEquals(2,this.repo.findAllByDisponibilita(null).size());
		assertEquals(1,this.repo.findAllByDisponibilita(true).size());
		assertEquals(0,this.repo.findAllByDisponibilita(false).size());
	}

	@Test
	@Transactional
	void testingVeicoloService() {
		Alimentazione a= new Alimentazione();
		a.setNome("elettrica");
		Categoria c= new Categoria();
		c.setNome("auto");
		BigDecimal b1 = new BigDecimal("78.95");
		BigDecimal b2 = new BigDecimal("67.55");
		MultipartFile immagine=null;
		
		Veicolo v= new Veicolo();
		v.setAlimentazione(a);
		v.setCategoria(c);
		v.setColore("rosso");
		v.setCoordinataX(b1);
		v.setCoordinataY(b2);
		v.setDescrizione("VeicoloProva");
		v.setDisponibilita(true);
		v.setIndirizzo("via roma 99");
		v.setMarca("Nissan");
		v.setModello("Modello");
		v.setImmagine("Immagine.jpg");
		vsrv.saveVeicolo(v, immagine);
		assertEquals(4, this.repo.findAll().size());
	}


	

}
