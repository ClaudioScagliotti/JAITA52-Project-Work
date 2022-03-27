package com.group.projectwork.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.UpdateVeicoloDTO;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.ImageSaveException;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.factory.VeicoloFactory;
import com.group.projectwork.repository.VeicoloDB;
import com.group.projectwork.utility.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VeicoloSRV {

    @Autowired
    VeicoloDB vdb;

    @Autowired
    FileSRV fs;
    
    @Autowired
    VeicoloFactory factory;

    public List<Veicolo> getAll() {
        return this.vdb.findAll();
    }

    public Veicolo addVeicolo(Veicolo v){
    	return this.vdb.save(v);
    }
    
    public Veicolo updVeicolo(Veicolo v) {
    	if(this.vdb.existsById(v.getId()))
    		return this.vdb.save(v);
    	throw new EntityNotFoundException("Veicolo not found");
    }

    public List<Veicolo> getByDisponibilita(boolean b){
        return this.vdb.findAllByDisponibilita(b);
    }

    public Veicolo getVeicoloById(int id){
    	var v =  this.vdb.findById(id);
    	if(v.isEmpty())
    		return null;
    	else
    		return v.get();
    }

    public void deleteById(int id) {
    	this.vdb.deleteById(id);
    }

    private String buildImgPath(Veicolo v) {
    	return StringUtils.upTo(v.getMarca(), 5) +"_"+
				StringUtils.upTo(v.getModello(), 5)+"_"+
				StringUtils.random(5);
    }
    
    public Veicolo addVeicolo(CreateVeicoloDTO v) throws ImageSaveException, VeicoloParseException {
			var toAdd = factory.parse(v);
			return this.saveVeicolo(toAdd, v.getFile());
    }
 
    public Veicolo updVeicolo(UpdateVeicoloDTO v) throws ImageSaveException, VeicoloParseException {
			var toAdd = factory.parse(v);
			return this.saveVeicolo(toAdd, v.getFile());
    }
    
    public Veicolo saveVeicolo( Veicolo veicolo, MultipartFile immagine) throws ImageSaveException {

    	//controlla se abbiamo un img
		if (immagine != null) {
			try {
				String imgPath = fs.saveFile("img/veicoli",this.buildImgPath(veicolo),immagine);
				veicolo.setImmagine(imgPath);
			} catch (IOException e) {
				throw new ImageSaveException();
			}
		}
		return vdb.save(veicolo);
	}
}
