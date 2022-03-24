package com.group.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.projectwork.entity.Alimentazione;
import com.group.projectwork.entity.Categoria;
import com.group.projectwork.entity.Veicolo;

public interface VeicoloDB extends JpaRepository<Veicolo, Integer> {

	List<Veicolo> findAllByMarca(String brand);
	List<Veicolo> findAllByModello(String model);
	List<Veicolo> findAllByColore(String model);
	List<Veicolo> findAllByDescrizioneContains(String model);
	List<Veicolo> findAllByAlimentazione(Alimentazione a);
	List<Veicolo> findAllByCategoria(Categoria a);
}
