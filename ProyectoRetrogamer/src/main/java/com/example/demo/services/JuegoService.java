package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Juego;
import com.example.demo.model.Usuario;
import com.example.demo.model.Votacion;
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
	
	public Votacion addVotos(Juego aux, Usuario user, int nota) {	
		Votacion vt = new Votacion (aux, user, nota);
			if (aux.getVotos().isEmpty()) {
				aux.getVotos().add(vt);
				user.getVotos().add(vt);
			}
			else {
				if(aux.getVotos().contains(vt)) {
				int OldVt = aux.getVotos().indexOf(vt);
				int OldVtUser = user.getVotos().indexOf(vt);
				aux.getVotos().get(OldVt).setVoto(nota);
				user.getVotos().get(OldVtUser).setVoto(nota);
				}
				else {
					aux.getVotos().add(vt);
					user.getVotos().add(vt);
				}
		}
		return vt;
	}
	
	
}
