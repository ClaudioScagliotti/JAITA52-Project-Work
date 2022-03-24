package com.group.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.projectwork.entity.Utente;

@Repository
public interface UtenteDB extends JpaRepository<Utente, Integer> {
	Optional<Utente> findByEmail(String email);
}
