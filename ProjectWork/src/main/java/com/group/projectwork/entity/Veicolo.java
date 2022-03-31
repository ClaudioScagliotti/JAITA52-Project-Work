package com.group.projectwork.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
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
	
	@Column(name = "coordinata_x")
	private BigDecimal coordinataX;
	
	@Column(name = "coordinata_y")
	private BigDecimal coordinataY;
	
	@Column(name="disponibilita")
	private Boolean disponibile;
	
	@Column(nullable = false)
	private boolean visibile;

	private String immagine;

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
	public BigDecimal getCoordinataX() {
		return coordinataX;
	}
	public void setCoordinataX(BigDecimal coordinataX) {
		this.coordinataX = coordinataX;
	}
	public BigDecimal getCoordinataY() {
		return coordinataY;
	}
	public void setCoordinataY(BigDecimal coordinataY) {
		this.coordinataY = coordinataY;
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
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
}
