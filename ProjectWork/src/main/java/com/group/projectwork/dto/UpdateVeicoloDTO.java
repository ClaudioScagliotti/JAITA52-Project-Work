package com.group.projectwork.dto;

public class UpdateVeicoloDTO extends CreateVeicoloDTO{
    
	private int id;
	private String immagine;
	private Boolean disponibilita;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public Boolean getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(Boolean disponibilita) {
		this.disponibilita = disponibilita;
	}	
}
