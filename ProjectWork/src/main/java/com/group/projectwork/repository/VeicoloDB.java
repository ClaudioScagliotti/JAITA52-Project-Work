package com.group.projectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group.projectwork.entity.Alimentazione;
import com.group.projectwork.entity.Categoria;
import com.group.projectwork.entity.Veicolo;
import com.group.projectwork.projection.ChatDataProjection;

public interface VeicoloDB extends JpaRepository<Veicolo, Integer> {

	List<Veicolo> findAllByMarca(String brand);
	List<Veicolo> findAllByModello(String model);
	List<Veicolo> findAllByColore(String model);
	List<Veicolo> findAllByDescrizioneContains(String model);
	List<Veicolo> findAllByAlimentazione(Alimentazione a);
	List<Veicolo> findAllByCategoria(Categoria a);
	List<Veicolo> findAllByDisponibile(Boolean b);
	
	@Query(nativeQuery = true, value = 
			"select c.nome as nome, count(c.nome)*c.fattore as val"
			+ " from projectwork.veicolo v, projectwork.categoria c"
			+ " where v.categoria_id = c.id group by c.nome"
			+ " order by -val;")
	List<ChatDataProjection> getChartData();
}
