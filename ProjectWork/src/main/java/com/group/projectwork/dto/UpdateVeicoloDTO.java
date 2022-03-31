package com.group.projectwork.dto;

public class UpdateVeicoloDTO extends CreateVeicoloDTO{
    
	private int id;
	private String immagine;
	private Boolean disponibile;
	private Boolean visibile;
	
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
	public Boolean getDisponibile() {
		return disponibile;
	}
	public void setDisponibile(Boolean disponibile) {
		this.disponibile = disponibile;
	}
	public Boolean getVisibile() {
		return visibile;
	}
	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}	
}
