package com.group.projectwork.integration;

import java.util.List;

import com.group.projectwork.entity.Veicolo;

import com.group.projectwork.service.VeicoloSRV;
import com.group.projectwork.service.VeicoloSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veicolo")
public class VeicoloREST {
    @Autowired 
    VeicoloSRV vsrv;

    @GetMapping
    public List<Veicolo> getAll(){
        return vsrv.getAll();
    }
    @GetMapping("/{id}")
    public Veicolo getById(@PathVariable("id") int id){
        return vsrv.getVeicoloById(id);
    }
    @PostMapping
    public ResponseEntity<?> addVeicolo(@RequestParam(name="veicolo") Veicolo v){
         var saved=vsrv.addVeicolo(v);
        return ResponseEntity.ok(saved);
    }
    public List<Veicolo> getByDisponibilita(boolean b){
        return vsrv.getByDisponibilita(b);
    }




}
