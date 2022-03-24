package com.group.projectwork.service;

import java.util.List;

import com.group.projectwork.entity.Alimentazione;
import com.group.projectwork.repository.AlimentazioneDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlimentazioneSRV {

    @Autowired
    AlimentazioneDB db;

    public List<Alimentazione> getAll() {
        return this.db.findAll();
    }

    public Alimentazione getById(int id) {
    	var opt = this.db.findById(id);
    	if(opt.isEmpty())
    		return null;
    	return opt.get();
    }  
}
