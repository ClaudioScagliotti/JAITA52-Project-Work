package com.group.projectwork;

import com.group.projectwork.repository.VeicoloRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectWorkApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testVeicoli(@Autowired VeicoloRepo vr){
		System.out.println( vr.findAll());
		
	}
}
