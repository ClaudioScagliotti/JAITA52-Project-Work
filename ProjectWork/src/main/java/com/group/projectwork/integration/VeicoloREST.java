package com.group.projectwork.integration;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.UpdateVeicoloDTO;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.factory.VeicoloFactory;
import com.group.projectwork.service.TokenSRV;
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
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<List<Veicolo>> getAll() {
		return ResponseEntity.ok(this.vsrv.getAll());
	}

	@GetMapping("/disp/{disp}")
	public ResponseEntity<List<Veicolo>> getByDisponibilita(@PathVariable("disp") boolean b) {
		return ResponseEntity.ok(this.vsrv.getByDisponibilita(b));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Veicolo> getById(@PathVariable("id") int id) {
		return ResponseEntity.ok(this.vsrv.getVeicoloById(id));
	}

	@PostMapping
	public ResponseEntity<Veicolo> addVeicolo(@RequestBody() CreateVeicoloDTO v,
			@RequestParam(name = "image") MultipartFile img, @RequestParam(name = "token") String token) {

		if (!tokenSrv.isValid(token))
			return ResponseEntity.badRequest().build();
		
		try {
			var saved = this.vsrv.addVeicolo(v, img);
			return ResponseEntity.ok(saved);
		} catch (ImageSaveException | VeicoloParseException ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<Veicolo> updVeicolo(@RequestBody() UpdateVeicoloDTO v,
			@RequestParam(name = "image") MultipartFile img, @RequestParam(name = "token") String token) {

		if (!tokenSrv.isValid(token))
			return ResponseEntity.badRequest().build();

		try {
			var saved = this.vsrv.updVeicolo(v, img);
			return ResponseEntity.ok(saved);
		} catch (ImageSaveException | VeicoloParseException | EntityNotFoundException ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Veicolo> deleteById(@PathVariable("id") int id, @RequestParam(name = "token") String token) {

		if (!tokenSrv.isValid(token))
			return ResponseEntity.badRequest().build();

		this.vsrv.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
