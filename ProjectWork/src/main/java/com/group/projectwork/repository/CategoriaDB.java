package com.group.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.projectwork.entity.Categoria;

public interface CategoriaDB extends JpaRepository<Categoria, Integer> {
	
	Optional<Categoria> findById(int id);
	Optional<Categoria> findByNome(String name);
}
