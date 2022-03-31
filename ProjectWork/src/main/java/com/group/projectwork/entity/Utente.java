package com.group.projectwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "utente")
public class Utente {
	
	public enum Role{
		RUOLO_ADMIN,
		RUOLO_UTENTE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private String cognome;
	
	@Column(name = "data_nascita")
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
	
	//specify that the email has to be unique
	@Column(unique = true, nullable = false, length = 50)
	private String email;
	
	//specify that doesn't have to be included in an eventual json
	@JsonIgnore
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role ruolo;
	
	@JsonIgnore
	@OneToOne(mappedBy = "utente")
	private Token token;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRuolo() {
		return ruolo;
	}
	public void setRuolo(Role ruolo) {
		this.ruolo = ruolo;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
}
