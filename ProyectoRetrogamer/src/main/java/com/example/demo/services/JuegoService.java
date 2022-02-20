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
	
	public List<Juego> getByCategoria(String categoria) {
		return repositorio.getByCategoria(categoria);
	}
	
	public List<Juego> getByTitulo(String titulo) {
		return repositorio.getByTitulo("%"+titulo+"%");
	}
	
	public List<Juego> getByDesarrollador(String desarrollador) {
		return repositorio.getByDesarrollador(desarrollador);
	}
	
	public List<Juego> getByYear(String year) {
		return repositorio.getByYear(year);
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
	
	public Votacion findByGameUser (long ref, Usuario user) {
		Juego aux=  repositorio.findById(ref).orElse(null);
		Votacion vt = new Votacion (aux, user, 2); 
		if(aux.getVotos().contains(vt)) {
			int vtAEditar = aux.getVotos().indexOf(vt);
			return  aux.getVotos().get(vtAEditar);
		}
		else {
			return null; 
			}

		}
	
	public void addReview(Votacion vt, String review) {
		Juego aux= vt.getJuego(); 
		Usuario user= vt.getUsuario(); 
		if(aux.getVotos().contains(vt)) {
			int OldVt = aux.getVotos().indexOf(vt);
			int OldVtUser = user.getVotos().indexOf(vt);
			aux.getVotos().get(OldVt).setReview(review);
			user.getVotos().get(OldVtUser).setReview(review);
			}
		
	}
	
}
	
	
