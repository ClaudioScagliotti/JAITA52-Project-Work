package com.group.projectwork.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class CreateVeicoloDTO {
    
	private int categoria;
	private int alimentazione;
	private String marca;
	private String modello;
	private String colore;
	private String descrizione;
	private String indirizzo;
	private MultipartFile file;
	private BigDecimal x;
	private BigDecimal y;
	
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getAlimentazione() {
		return alimentazione;
	}
	public void setAlimentazione(int alimentazione) {
		this.alimentazione = alimentazione;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public BigDecimal getX() {
		return x;
	}
	public void setX(BigDecimal x) {
		this.x = x;
	}
	public BigDecimal getY() {
		return y;
	}
	public void setY(BigDecimal y) {
		this.y = y;
	}
}
