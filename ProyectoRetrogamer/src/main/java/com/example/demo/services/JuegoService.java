package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Juego;
import com.example.demo.model.Usuario;
import com.example.demo.model.Votacion;
import com.example.demo.repository.JuegoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class JuegoService {
	
	@Autowired
	private JuegoRepository repositorio;
	
	@Autowired
	private UsuarioRepository repoUser; 
	
	

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
	
	public Votacion addVotos(Votacion vt) {	
			if (vt.getJuego().getVotos().isEmpty()) {
				vt.getJuego().getVotos().add(vt);
				repositorio.save(vt.getJuego());
			}
			else {
				if(vt.getJuego().getVotos().contains(vt)) {
				int OldVt = vt.getJuego().getVotos().indexOf(vt);
				vt.getJuego().getVotos().get(OldVt).setVoto(vt.getVoto());
				repositorio.save(vt.getJuego());
				}
				else {
					vt.getJuego().getVotos().add(vt);
					repositorio.save(vt.getJuego());
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
			repositorio.save(aux);
			}
		
	}
	
}
	
	
