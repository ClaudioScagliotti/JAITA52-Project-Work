package com.group.projectwork.integration;

import static com.group.projectwork.utility.ErrorUtils.accessDeniedMVC;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.UpdateVeicoloDTO;
import com.group.projectwork.entity.Utente;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.entity.Utente.Role;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.factory.VeicoloFactory;
import com.group.projectwork.service.TokenSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/veicolo")
public class VeicoloREST {
	@Autowired
	VeicoloSRV vsrv;

	@Autowired
	VeicoloFactory factory;

	@Autowired
	TokenSRV tokenSrv;

	@GetMapping
	public ResponseEntity<List<Veicolo>> getAll(
			@RequestParam(name = "token", required = false, defaultValue = "") String token) {
		
		if(token!=null) {
			
		}
		return ResponseEntity.ok(this.vsrv.getAll());
	}

	@GetMapping("/disp/{disp}")
	public ResponseEntity<List<Veicolo>> getByDisponibilita(
			@RequestParam(name = "token", required = false, defaultValue = "") String token,
			@PathVariable("disp") boolean b) {
		
		if(token!=null && tokenSrv.isValid(token,Role.RUOLO_ADMIN)) {
			return ResponseEntity.ok(this.vsrv.getByDisponibilita(b));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UpdateVeicoloDTO> getById(
			@RequestParam(name = "token") String token,
			@PathVariable("id") int id) {
		
		if (tokenSrv.isValid(token,Role.RUOLO_ADMIN))
		{
			var selected = this.vsrv.getVeicoloById(id);
			var dto = factory.createDto(selected);
			return ResponseEntity.ok(dto);			
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping
	public ResponseEntity<Veicolo> addVeicolo(@RequestBody() CreateVeicoloDTO v,
			@RequestParam(name = "token") String token) {

		if (!tokenSrv.isValid(token,Role.RUOLO_ADMIN))
			return ResponseEntity.badRequest().build();
		
		try {
			var saved = this.vsrv.addVeicolo(v);
			return ResponseEntity.ok(saved);
		} catch (ImageSaveException | VeicoloParseException ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<Veicolo> updVeicolo(@RequestBody() UpdateVeicoloDTO v,
			@RequestParam(name = "token") String token) {

		if (!tokenSrv.isValid(token,Role.RUOLO_ADMIN))
			return ResponseEntity.badRequest().build();

		try {
			var saved = this.vsrv.updVeicolo(v);
			return ResponseEntity.ok(saved);
		} catch (ImageSaveException | VeicoloParseException | EntityNotFoundException ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Veicolo> deleteById(@PathVariable("id") int id, @RequestParam(name = "token") String token) {

		if (!tokenSrv.isValid(token,Role.RUOLO_ADMIN))
			return ResponseEntity.badRequest().build();

		try {
			this.vsrv.deleteById(id);
			return ResponseEntity.ok().build();
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.unprocessableEntity().build();
		}

	}
	
	@GetMapping("/chart")
	public ResponseEntity<?> getChartData() {
		var data = this.vsrv.getChartData();
		return ResponseEntity.ok(data);
	}
}
