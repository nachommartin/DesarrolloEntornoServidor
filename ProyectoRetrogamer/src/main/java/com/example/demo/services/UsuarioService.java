package com.example.demo.services;

import java.util.Date;
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
	
	
	public Amistad unfollowUser(String correoSource, String correoTarget) {
		Usuario userFollower= this.getByMail(correoTarget);
		Usuario userFollowed= this.getByMail(correoSource);
		Amistad ami= new Amistad(userFollowed, userFollower);
		for(int i = 0;i<userFollowed.getAmigos().size();i++) {
			if (userFollowed.getAmigos().get(i).getUsuario().equals(userFollowed)&&
					userFollowed.getAmigos().get(i).getFollower().equals(userFollower)) {
				Amistad aux= userFollowed.getAmigos().get(i);
				userFollowed.getAmigos().remove(aux);
				repositorio.save(userFollowed); 
			}
		}
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
	
	public Comentario sendComentario(String comentario, String correoSource, String correoTarget) {
		Usuario userReceptor= this.getByMail(correoTarget);
		Usuario userEmisor= this.getByMail(correoSource);
		Comentario aux= new Comentario(comentario, userEmisor, userReceptor); 
		aux.setComentario(aux.getComentario()+aux.getFecha());
		userReceptor.getComentarios().add(aux); 
		repositorio.save(userReceptor); 
		return aux; 		
	}
	
	public Comentario updateComentario(String correoTarget, String comentario, long ref) {
		Usuario userReceptor= this.getByMail(correoTarget);
		Comentario aux= new Comentario();
		aux.setCodigoComentario(ref); 
		userReceptor.getComentarios();
		int buscador= userReceptor.getComentarios().indexOf(aux);
		if (buscador == -1) {
			return null; 
		}
		else {
			Comentario aRescatar = userReceptor.getComentarios().get(buscador);
			Date fecha= new Date();
			comentario= "Comentario de "+aRescatar.getUsuarioEmisor().getNick()+" para ti:\n"+comentario+"\n"+"Enviado el ";
			userReceptor.getComentarios().get(buscador).setComentario(comentario+fecha);
			userReceptor.getComentarios().get(buscador).setFecha(fecha);
			repositorio.save(userReceptor); 
		}
		return userReceptor.getComentarios().get(buscador); 
	}
	

}
