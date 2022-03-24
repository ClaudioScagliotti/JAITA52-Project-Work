package com.group.projectwork.service;

import java.util.List;

import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.repository.VeicoloDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeicoloSRV {

    @Autowired
    VeicoloDB vdb;

    public List<Veicolo> getAll() {
        return this.vdb.findAll();
    }

    public Veicolo addVeicolo(Veicolo v){
       return this.vdb.save(v);
    }

    public List<Veicolo> getByDisponibilita(boolean b){
        return this.vdb.findAllByDisponibilita(b);
    }

    public Veicolo getVeicoloById(int id){
        return this.vdb.getById(id);
    }


    
}
