package com.group.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.projectwork.entity.Token;
import com.group.projectwork.entity.Utente;

public interface TokenDB extends JpaRepository<Token, Integer> {

	Optional<Token> findByUtente(Utente u);
	Optional<Token> findByValore(String v);
}
