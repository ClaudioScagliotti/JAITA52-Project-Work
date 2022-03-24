package com.group.projectwork.integration;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.UpdateVeicoloDTO;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.factory.VeicoloFactory;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veicolo")
public class VeicoloREST {
    @Autowired 
    VeicoloSRV vsrv;
    
    @Autowired
    VeicoloFactory factory;

    @GetMapping
    public ResponseEntity<List<Veicolo>> getAll(){
        return ResponseEntity.ok(this.vsrv.getAll());
    }
    
    @GetMapping("/disp/{disp}")
    public ResponseEntity<List<Veicolo>> getByDisponibilita(@PathVariable("disp") boolean b){
        return ResponseEntity.ok(this.vsrv.getByDisponibilita(b));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Veicolo> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(this.vsrv.getVeicoloById(id));
    }
    
    @PostMapping
    public ResponseEntity<Veicolo> addVeicolo(
    		@RequestBody() CreateVeicoloDTO v,
    		@RequestParam(name="token") String token){
    	
    	//TODO check sul token
    	
    	try {
    		var toAdd = factory.parse(v);
    		var saved=vsrv.addVeicolo(toAdd);
    		//TODO addImg
    		return ResponseEntity.ok(saved);
    	}
    	catch(VeicoloParseException ex)
    	{
    		//TODO specificare errore
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    @PutMapping
    public ResponseEntity<Veicolo> updVeicolo(
    		@RequestBody() UpdateVeicoloDTO v,
    		@RequestParam(name="token") String token){
    	
    	//TODO check sul token
    	
    	try {
    		var toAdd = factory.parse(v);
    		var saved=vsrv.updVeicolo(toAdd);
    		//TODO addImg
    		return ResponseEntity.ok(saved);
    	}
    	catch(VeicoloParseException|EntityNotFoundException ex )
    	{
    		//TODO specificare errore
    		return ResponseEntity.badRequest().build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Veicolo> deleteById(
    		@PathVariable("id") int id,
    		@RequestParam(name="token") String token){
    	
    	//TODO check sul token
    	
    	this.vsrv.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}