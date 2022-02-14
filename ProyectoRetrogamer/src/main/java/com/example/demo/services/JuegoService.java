package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Juego;
import com.example.demo.repository.JuegoRepository;

@Service
public class JuegoService {
	
	@Autowired
	private JuegoRepository repositorio;
	
	

	public Juego getByRef(long ref) {
		return repositorio.findById(ref).orElse(null);
	}
	
	public List<Juego> mostrarJuegos() {
		return repositorio.findAll();
	}
	
}
