package com.group.projectwork.dto;

public class UpdateVeicoloDTO extends CreateVeicoloDTO{
    
	private int id;
	private Boolean disponibilita;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(Boolean disponibilita) {
		this.disponibilita = disponibilita;
	}	
}
