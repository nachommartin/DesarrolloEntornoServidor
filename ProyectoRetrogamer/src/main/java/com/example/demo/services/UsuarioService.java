package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Juego;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	public Usuario getByMail(String correo) {
		return repositorio.findById(correo).orElse(null);
	}
	
	public List<Usuario> mostrarUsuarios() {
		return repositorio.findAll();
	}
	

}
