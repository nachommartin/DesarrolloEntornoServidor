package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Amistad;
import com.example.demo.model.Comentario;
import com.example.demo.model.Juego;
import com.example.demo.model.Usuario;
import com.example.demo.model.Votacion;
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
	
	public Amistad followUser(String correoSource, String correoTarget) {
		Usuario userFollowed= this.getByMail(correoTarget);
		Usuario userFollower= this.getByMail(correoSource);
		Amistad ami= new Amistad(userFollower, userFollowed);
		userFollowed.getAmigos().add(ami);
		repositorio.save(userFollowed); 
		return ami; 		
	}
	
	
	public List<Votacion>verVotos(String correoSource, String correoTarget) {
		Usuario userFollowed= this.getByMail(correoTarget);
		Usuario userStalker= this.getByMail(correoSource);
		Amistad aux= new Amistad(userStalker, userFollowed);
		if (userStalker.getAmigos().contains(aux)){
			return userFollowed.getVotos();
		}
		else {
			return null;
			
		}	

		
	}
	
	public Comentario sendComentario(String correoSource, String correoTarget, String comentario) {
		Usuario userReceptor= this.getByMail(correoTarget);
		Usuario userEmisor= this.getByMail(correoSource);
		Comentario aux= new Comentario(comentario, userEmisor, userReceptor); 
		aux.setComentario(aux.getComentario()+aux.getFecha());
		userReceptor.getComentarios().add(aux); 
		repositorio.save(userReceptor); 
		return aux; 		
	}
	

}
