package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AmigoDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.VotoDTO;
import com.example.demo.error.ApiError;
import com.example.demo.error.JuegoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.error.VotoException;
import com.example.demo.model.Amistad;
import com.example.demo.model.Juego;
import com.example.demo.model.Usuario;
import com.example.demo.model.Votacion;
import com.example.demo.services.JuegoService;
import com.example.demo.services.UsuarioService;

@RestController
public class MainController {
	
	@Autowired
	private UsuarioService servicioUser; 
	
	@Autowired
	private JuegoService servicioGame; 
	
	@GetMapping("/usuario/{user}")
	public Usuario findByUser(@PathVariable String user) {
		Usuario resultado = servicioUser.getByMail(user);
		if (resultado == null) {
			throw new UsuarioNotFoundException(user);
		} else {
			return resultado;
		}
	}
	
	
	
	@GetMapping("/usuario")
	@ResponseBody
	public JSONObject getUser(@RequestParam(required = false) String correo) { 
		Usuario resultado = servicioUser.getByMail(correo);
		if (resultado == null) {
		throw new UsuarioNotFoundException(correo);
		}
		else {
		String cadenaParseo= "{\"correo\":\""+ correo+"\"}";  
		JSONObject json= new JSONObject(cadenaParseo);
	    return json;
		}
	}
	
	  @PostMapping("/usuario/{user}/amistad")
		public Amistad follow(@PathVariable String user, @RequestBody AmigoDTO userAskToFollow) {
			Usuario userFollowed = servicioUser.getByMail(user);
			Usuario userFollower = servicioUser.getByMail(userAskToFollow.getCorreo());
			if (userFollowed == null) {
				throw new UsuarioNotFoundException(user);
			} 
			else if (userFollower == null) {
				throw new UsuarioNotFoundException(userAskToFollow.getCorreo());
			} 
			Amistad ami = servicioUser.followUser(user, userAskToFollow.getCorreo());
			return ami;
		}
	    
	
	
	
	@GetMapping("/juego")
	@ResponseBody
	public List<Juego> getYear(@RequestParam(required = false) String year, @RequestParam(required = false) String titulo,
			@RequestParam(required = false) String desarrollador, @RequestParam(required = false) String categoria) { 
		if (year!= null) {
			return this.servicioGame.getByYear(year);
	}
		else if (categoria !=null) {
			return this.servicioGame.getByCategoria(categoria);
		}
		else if(desarrollador !=null) {
			return this.servicioGame.getByDesarrollador(desarrollador);
		}
		else if (titulo !=null) {
			return this.servicioGame.getByTitulo(titulo);
		}
		else {
			return this.servicioGame.mostrarJuegos();
		}
		
	}
	
	@GetMapping("/juego/{ref}")
	public Juego findByUser(@PathVariable long ref) {
		Juego resultado = servicioGame.getByRef(ref);
		if (resultado == null) {
			throw new JuegoNotFoundException(ref);
		} else {
			return resultado;
		}
	}
	
    @PostMapping("/juego/{ref}/votacion")
	public Votacion add(@PathVariable long ref, @RequestBody VotoDTO voto) {
		Juego resultado = servicioGame.getByRef(ref);
		Usuario user = servicioUser.getByMail(voto.getCorreo());
		if (resultado == null) {
			throw new JuegoNotFoundException(ref);
		} 
		else if(voto.getVoto() <0 || voto.getVoto() >10) {
			throw new VotoException();
			
		}
		else if (user == null) {
			throw new UsuarioNotFoundException(voto.getCorreo());
		} 
		Votacion vt = new Votacion(resultado, user, voto.getVoto());
		servicioGame.addVotos(vt);
		return vt;
	}
    
    @PutMapping("/juego/{ref}/votacion")
   	public Votacion addReview(@PathVariable long ref, @RequestBody ReviewDTO review) {
   		Juego resultado = servicioGame.getByRef(ref);
   		Usuario user = servicioUser.getByMail(review.getCorreo());
   		if (resultado == null) {
   			throw new JuegoNotFoundException(ref);
   		} 
   		else if (user == null) {
   			throw new UsuarioNotFoundException(review.getCorreo());
   		} 
   		Votacion vt = servicioGame.findByGameUser(ref, user); 
   		servicioGame.addReview(vt, review.getReview());
   		return vt;
   	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> handleUsuarioNoEncontrado(UsuarioNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(JuegoNotFoundException.class)
	public ResponseEntity<ApiError> handleJuegoNoEncontrado(JuegoNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

}
