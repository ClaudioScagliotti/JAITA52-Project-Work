package com.group.projectwork.service;

import java.util.List;

import com.group.projectwork.entity.Categoria;
import com.group.projectwork.repository.CategoriaDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaSRV {

    @Autowired
    CategoriaDB db;

    public List<Categoria> getAll() {
        return this.db.findAll();
    }

    public Categoria getById(int Id) {
    	var opt = this.db.findById(Id);
    	if(opt.isEmpty())
    		return null;
    	return opt.get();
    }   
}
