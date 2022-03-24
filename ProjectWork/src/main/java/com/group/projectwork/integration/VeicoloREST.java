package com.group.projectwork.integration;

import java.util.List;

import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.repository.VeicoloRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veicolo")
public class VeicoloREST {
    
    @Autowired 
    VeicoloRepo vr;

    @GetMapping
    public List<Veicolo> findAll(){
        return vr.findAll();
    }
}
