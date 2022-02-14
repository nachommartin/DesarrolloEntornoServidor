package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Juego;

public interface JuegoRepository extends JpaRepository<Juego,Long> {

}
