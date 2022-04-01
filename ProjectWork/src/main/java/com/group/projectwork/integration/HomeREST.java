package com.group.projectwork.integration;

import com.group.projectwork.entity.Categoria;
import com.group.projectwork.service.CategoriaSRV;
import com.group.projectwork.service.UtenteSRV;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeREST {
	
    @Autowired 
     UtenteSRV usrv;
    
    @Autowired
    CategoriaSRV catSrv;

	@GetMapping("categories")
	public ResponseEntity<List<Categoria>> logout(){
		var categories = this.catSrv.getAll();
		return ResponseEntity.ok(categories);
	}
    
}
