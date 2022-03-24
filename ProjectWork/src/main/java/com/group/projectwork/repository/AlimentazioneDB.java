package com.group.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.projectwork.entity.Alimentazione;

public interface AlimentazioneDB extends JpaRepository<Alimentazione, Integer> {

	Optional<Alimentazione> findByNome(String nome);
}
