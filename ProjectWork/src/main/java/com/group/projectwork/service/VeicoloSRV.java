package com.group.projectwork.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.repository.VeicoloDB;
import com.group.projectwork.service.FileSRV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VeicoloSRV {

    @Autowired
    VeicoloDB vdb;

    @Autowired
    FileSRV fs;

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
        return this.vdb.findById(id).get();
    }

    public void deleteById(int id) {
    	this.vdb.deleteById(id);
    }

    public Veicolo saveVeicolo( Veicolo veicoloForm, MultipartFile immagine) {

		if (immagine != null) {
			try {
				String percorso = fs.saveFile("img/veicoli", veicoloForm.getDescrizione() + immagine.getName(),
						immagine);
				veicoloForm.setImmagine(percorso);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return vdb.save(veicoloForm);
	}
}
