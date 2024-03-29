package com.example.demo.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Amistad;
import com.example.demo.model.Comentario;
import com.example.demo.model.Usuario;
import com.example.demo.model.Votacion;
import com.example.demo.repository.UsuarioRepository;

/** 
 * Lógica de negocio que repercute en el usuario
 * @author Nacho
 *
 */
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	/**
	 * Método para encontrar a un usuario por su correo electrónico (su PK/ID)
	 * @param correo
	 * @return
	 */
	public Usuario getByMail(String correo) {
		return repositorio.findById(correo).orElse(null);
	}
	
	/**
	 * Método para recuperar todos los usuarios
	 * @return
	 */
	public List<Usuario> mostrarUsuarios() {
		return repositorio.findAll();
	}
	
	/**
	 * Método para seguir a un usuario
	 * @param correoSource
	 * @param correoTarget
	 * @return
	 */
	public Amistad followUser(String correoSource, String correoTarget) {
		Usuario userFollowed= this.getByMail(correoTarget);
		Usuario userFollower= this.getByMail(correoSource);
		Amistad ami= new Amistad(userFollower, userFollowed);
		userFollowed.getAmigos().add(ami);
		repositorio.save(userFollowed); 
		return ami; 		
	}
	
	
	/**
	 * Método para dejar de seguir a un usuario
	 * @param correoSource
	 * @param correoTarget
	 * @return
	 */
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
	
	/**
	 * Método para ver los votos de un usuario
	 * @param correoSource
	 * @param correoTarget
	 * @return
	 */
	public List<Votacion>verVotos(String correoSource, String correoTarget) {
		Usuario userFollowed= this.getByMail(correoTarget);
		Usuario userStalker= this.getByMail(correoSource);
		Amistad aux= new Amistad();		
		for(int i = 0;i<userFollowed.getAmigos().size();i++) {
			if (userFollowed.getAmigos().get(i).getUsuario().equals(userFollowed)&&
					userFollowed.getAmigos().get(i).getFollower().equals(userStalker)) {
				 aux= userFollowed.getAmigos().get(i);
			}
		}
		if (userFollowed.getAmigos().contains(aux)){
			return userFollowed.getVotos();
		}
		else {
			return null;
			
		}	

		
	}
	
	/**
	 * Método para enviar un comentario a un usuario
	 * @param comentario
	 * @param correoSource
	 * @param correoTarget
	 * @return
	 */
	public Comentario sendComentario(String comentario, String correoSource, String correoTarget) {
		Usuario userReceptor= this.getByMail(correoTarget);
		Usuario userEmisor= this.getByMail(correoSource);
		Comentario aux= new Comentario(comentario, userEmisor, userReceptor); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String fechaTexto = formatter.format(aux.getFecha());
		aux.setTexto(aux.getTexto()+fechaTexto);
		userReceptor.getComentarios().add(aux); 
		repositorio.save(userReceptor); 
		return aux; 		
	}
	
	/**
	 * Método para editar un comentario ya enviado
	 * @param correoTarget
	 * @param comentario
	 * @param ref
	 * @return
	 */
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
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
			String fechaTexto = formatter.format(fecha);
			comentario= "Comentario de "+aRescatar.getUsuarioEmisor().getNick()+" para ti:\n"+comentario+"\n"+"Enviado el ";
			userReceptor.getComentarios().get(buscador).setTexto(comentario+fechaTexto);
			userReceptor.getComentarios().get(buscador).setFecha(fecha);
			repositorio.save(userReceptor); 
		}
		return userReceptor.getComentarios().get(buscador); 
	}
	
	/**
	 * Método para borrar un comentario
	 * @param correoTarget
	 * @param ref
	 * @return
	 */
	public Comentario deleteComentario(String correoTarget, long ref) {
		Usuario userReceptor= this.getByMail(correoTarget);
		Comentario aux= new Comentario();
		aux.setCodigoComentario(ref); 
		userReceptor.getComentarios();
		int buscador= userReceptor.getComentarios().indexOf(aux);
		if (buscador == -1) {
			return null; 
		}
		else {
			aux = userReceptor.getComentarios().get(buscador);
			userReceptor.getComentarios().remove(buscador);
			repositorio.save(userReceptor); 
		}
		return aux; 
	}
	

}
