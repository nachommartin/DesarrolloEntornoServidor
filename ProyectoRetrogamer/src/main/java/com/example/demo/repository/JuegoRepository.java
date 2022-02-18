package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Juego;

public interface JuegoRepository extends JpaRepository<Juego,Long> {
	
	@Query("select j from Juego j where j.categoria like ?1")
	Juego getByCategoria(String categoria); 

}
