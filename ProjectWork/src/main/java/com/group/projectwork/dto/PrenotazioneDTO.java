package com.group.projectwork.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PrenotazioneDTO {

	
	private int vId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ssXXX")
	private Date inizio;
	
	//"2022-05-01 17:10:10+02:00" parse come
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ssXXX")
	private Date fine;
	
	
	public int getvId() {
		return vId;
	}
	public void setvId(int vId) {
		this.vId = vId;
	}
	public Date getInizio() {
		return inizio;
	}
	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}
	public Date getFine() {
		return fine;
	}
	public void setFine(Date fine) {
		this.fine = fine;
	}
	
}
