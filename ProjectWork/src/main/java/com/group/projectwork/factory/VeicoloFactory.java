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
	
	public UpdateVeicoloDTO createDto(Veicolo v) {
		var dto = new UpdateVeicoloDTO();
		dto.setAlimentazione(v.getAlimentazione().getId());
		dto.setCategoria(v.getCategoria().getId());
		dto.setColore(v.getColore());
		dto.setX(v.getCoordinataX());
		dto.setY(v.getCoordinataY());
		dto.setDescrizione(v.getDescrizione());
		dto.setDisponibile(v.getDisponibile());
		dto.setVisibile(v.getVisibile());
		dto.setId(v.getId());
		dto.setIndirizzo(v.getIndirizzo());
		dto.setMarca(v.getMarca());
		dto.setModello(v.getModello());
		dto.setImmagine(v.getImmagine());
		return dto;
	}
	
	public Veicolo parse(UpdateVeicoloDTO dto) throws VeicoloParseException {
		var parsed = this.parse((CreateVeicoloDTO)dto);
		parsed.setId(dto.getId());
		parsed.setDisponibile(dto.getDisponibile());
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
		parsed.setCoordinataX(dto.getX());
		parsed.setCoordinataY(dto.getY());
		parsed.setDescrizione(dto.getDescrizione());
		parsed.setDisponibile(null);
		parsed.setIndirizzo(dto.getIndirizzo());
		parsed.setMarca(dto.getMarca());
		parsed.setModello(dto.getModello());
		return parsed;
	}	
}
