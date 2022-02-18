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

import com.example.demo.error.ApiError;
import com.example.demo.error.JuegoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.error.VotoException;
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
		String cadenaParseo= "{\"correo\":\""+ correo+"\"}";  
		JSONObject json= new JSONObject(cadenaParseo);
	    return json;
	}
	
	
	@GetMapping("/juego")
	public List<Juego> findAll(){
		return this.servicioGame.mostrarJuegos();
	}
	
	/*@GetMapping("/juego")
	@ResponseBody
	public Juego getYear(@RequestParam(required = false) String year, @RequestParam(required = false) String titulo,
			@RequestParam(required = false) String desarrollador, @RequestParam(required = false) String categoria) { 
		if (year!= null) {
			return 
		}
	}*/
	
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
	public Votacion add(@PathVariable long ref, @RequestBody int voto, @RequestBody String correo) {
		Juego resultado = servicioGame.getByRef(ref);
		Usuario user = servicioUser.getByMail(correo);
		if (resultado == null) {
			throw new JuegoNotFoundException(ref);
		} 
		else if(voto <0 || voto >10) {
			throw new VotoException();
			
		}
		else if (user == null) {
			throw new UsuarioNotFoundException(correo);
		} 
		Votacion vt = new Votacion(resultado, user, voto);
		return vt;
	}
    
    @PutMapping("/juego/{ref}/votacion")
   	public Votacion addReview(@PathVariable long ref, @RequestBody String correo, @RequestBody String review) {
   		Juego resultado = servicioGame.getByRef(ref);
   		Usuario user = servicioUser.getByMail(correo);
   		if (resultado == null) {
   			throw new JuegoNotFoundException(ref);
   		} 
   		else if (user == null) {
   			throw new UsuarioNotFoundException(correo);
   		} 
   		Votacion vt = servicioGame.findByGameUser(ref, user); 
   		servicioGame.addReview(vt, review);
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
