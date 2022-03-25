package com.group.projectwork;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.group.projectwork.entity.Alimentazione;
import com.group.projectwork.entity.Categoria;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.repository.AlimentazioneDB;
import com.group.projectwork.repository.CategoriaDB;
import com.group.projectwork.repository.VeicoloDB;
import com.group.projectwork.service.FileSRV;
import com.group.projectwork.service.VeicoloSRV;

@SpringBootTest
class LocalFileTest {

	@Autowired
	FileSRV srv;

	@Test
	@Transactional
	void testingVeicoloService() {
		try {
			final String filename = "index.jpg";
			File file = new File("C://index.jpg");
			final MultipartFile multipartFile = new MockMultipartFile(filename,filename,"image/jpg",new FileInputStream(file));
			final String savedPath = srv.saveFile("imgTest", filename, multipartFile);
			
			File file_saved = new File("src/main/resources/static/imgTest/index.jpg");
			if(!file_saved.exists())
				fail();
			
			assertEquals("/imgTest/index.jpg", savedPath);
			
		} catch (IOException e) {
			System.out.println(e);
			fail();
		}
	}


	

}
