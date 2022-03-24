package com.group.projectwork.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.projectwork.dto.CreateVeicoloDTO;
import com.group.projectwork.dto.UpdateVeicoloDTO;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.exception.VeicoloParseException;
import com.group.projectwork.service.AlimentazioneSRV;
import com.group.projectwork.service.CategoriaSRV;

@Service
public class VeicoloFactory {
	
	@Autowired
	CategoriaSRV catSrv;
	
	@Autowired
	AlimentazioneSRV aliSrv;
	
	public Veicolo parse(UpdateVeicoloDTO dto) throws VeicoloParseException {
		var parsed = this.parse((CreateVeicoloDTO)dto);
		parsed.setId(dto.getId());
		parsed.setDisponibilita(dto.getDisponibilita());
		return parsed;
	}
	
	public Veicolo parse(CreateVeicoloDTO dto) throws VeicoloParseException {
		
		var parsed = new Veicolo();
		var cat = catSrv.getById(dto.getCategoria());
		var ali = aliSrv.getById(dto.getAlimentazione());
		if(cat == null || ali == null) {
			//TODO exception
			throw new VeicoloParseException("Ali or Cat is nul");
		}
		parsed.setAlimentazione(ali);
		parsed.setCategoria(cat);
		parsed.setColore(dto.getColore());
		parsed.setCoordinataX(dto.getCoordinataX());
		parsed.setCoordinataY(dto.getCoordinataY());
		parsed.setDescrizione(dto.getDescrizione());
		parsed.setDisponibilita(null);
		parsed.setIndirizzo(dto.getIndirizzo());
		parsed.setMarca(dto.getMarca());
		parsed.setModello(dto.getModello());
		return parsed;
	}	
}
