package com.group.projectwork.Entity;



import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "veicolo")
public class Veicolo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="alimentazione_id")
	private Alimentazione alimentazione;
	
	private String marca;
	private String modello;
	private String colore;
	private String descrizione;
	private String indirizzo;
	private BigDecimal coordinata_x;
	private BigDecimal coordinata_y;
	private Boolean disponibilita;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Alimentazione getAlimentazione() {
		return alimentazione;
	}
	public void setAlimentazione(Alimentazione alimentazione) {
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
	public BigDecimal getCoordinata_x() {
		return coordinata_x;
	}
	public void setCoordinata_x(BigDecimal coordinata_x) {
		this.coordinata_x = coordinata_x;
	}
	public BigDecimal getCoordinata_y() {
		return coordinata_y;
	}
	public void setCoordinata_y(BigDecimal coordinata_y) {
		this.coordinata_y = coordinata_y;
	}
	public Boolean getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(Boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	@Override
	public String toString() {
		return "Veicolo [id=" + id + ", categoria=" + categoria + ", alimentazione=" + alimentazione + ", marca="
				+ marca + ", modello=" + modello + ", colore=" + colore + ", descrizione=" + descrizione
				+ ", indirizzo=" + indirizzo + ", coordinata_x=" + coordinata_x + ", coordinata_y=" + coordinata_y
				+ ", disponibilita=" + disponibilita + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
