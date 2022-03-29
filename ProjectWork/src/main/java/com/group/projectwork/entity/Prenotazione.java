package com.group.projectwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {
	
	public enum State{
		Corrente,
		Annullato,
		Completato,
		Prenotato
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name="veicolo_id")
	private Veicolo veicolo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonIgnore
	private Date ins;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inizio_noleggio")
	private Date inizio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fine_noleggio")
	private Date fine;
	
	@Enumerated(EnumType.STRING)
	private State stato;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Veicolo getVeicolo() {
		return veicolo;
	}

	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}

	public Date getIns() {
		return ins;
	}

	public void setIns(Date ins) {
		this.ins = ins;
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

	public State getStato() {
		return stato;
	}

	public void setStato(State stato) {
		this.stato = stato;
	}


	
	
	
	
}
