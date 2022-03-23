package com.group.projectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.projectwork.Entity.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

}
