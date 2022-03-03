package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ActualizaMensajeDTO;
import com.example.demo.dto.AmigoDTO;
import com.example.demo.dto.ComentarioDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.VotoDTO;
import com.example.demo.error.ApiError;
import com.example.demo.error.ComentarioException;
import com.example.demo.error.ForbiddenVotosException;
import com.example.demo.error.JuegoNotFoundException;
import com.example.demo.error.MensajeException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.error.VotarAntesException;
import com.example.demo.error.VotoException;
import com.example.demo.model.Amistad;
import com.example.demo.model.Comentario;
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
	
	@GetMapping("/usuario/{user}/votacion")
	public List<Votacion> getVotesByUser(@PathVariable String user, @RequestBody(required = false) AmigoDTO stalker) {
		Usuario resultado = servicioUser.getByMail(user);
		if (resultado == null) {
			throw new UsuarioNotFoundException(user);
		} 
		else if (stalker != null){
			if (servicioUser.verVotos(stalker.getCorreo(), user)==null) {
				throw new ForbiddenVotosException(user);
			}
			else {
				return servicioUser.verVotos(stalker.getCorreo(), user);
			}
		}
		else if (resultado.getVotos()==null) {
			List<Votacion> votos= new ArrayList(); 
			resultado.setVotos(votos);
			return resultado.getVotos();
		}
		else {
			return resultado.getVotos();
		}
	}
	
	
	@GetMapping("/usuario")
	public Usuario getUser() { 
		String correo= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = servicioUser.getByMail(correo);
	    return user;		
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
	  
	  @DeleteMapping("/usuario/{user}/amistad")
		public Amistad unfollow(@PathVariable String user, @RequestBody AmigoDTO userAskToFollow) {
			Usuario userFollowed = servicioUser.getByMail(user);
			Usuario userFollower = servicioUser.getByMail(userAskToFollow.getCorreo());
			if (userFollowed == null) {
				throw new UsuarioNotFoundException(user);
			} 
			else if (userFollower == null) {
				throw new UsuarioNotFoundException(userAskToFollow.getCorreo());
			} 
			Amistad ami = servicioUser.unfollowUser(user, userAskToFollow.getCorreo());
			return ami;
		}
	    
	
	
	@GetMapping("/juego")
	@ResponseBody
	public List<Juego> getGames(@RequestParam(required = false) String year, @RequestParam(required = false) String titulo,
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
	public Juego findByRef(@PathVariable long ref) {
		Juego resultado = servicioGame.getByRef(ref);
		if (resultado == null) {
			throw new JuegoNotFoundException(ref);
		} else {
			return resultado;
		}
	}
	
	@PostMapping("/juego")
	public Juego addJuego(@RequestBody Juego game) {
		this.servicioGame.addJuego(game);
			return game;
	}
	
	@PutMapping("/juego")
	public Juego updateJuego(@RequestBody Juego game) {
		Juego aux = this.servicioGame.updateJuego(game);
		if (aux == null) {
			throw new JuegoNotFoundException();
		}
		
			return game;
	}

	@DeleteMapping("/juego")
	public Juego removeJuego(@RequestBody Juego game) {
		Juego aux = this.servicioGame.removeJuego(game);
		if (aux == null) {
			throw new JuegoNotFoundException();
		}
		
			return game;
	}
	
	@GetMapping("/juego/{ref}/votacion")
	public List<Votacion> getVotesByGame(@PathVariable long ref) {
		Juego resultado = servicioGame.getByRef(ref);
		if (resultado == null) {
			throw new JuegoNotFoundException(ref);
		} 
		else if (resultado.getVotos()==null) {
			List<Votacion> votos= new ArrayList(); 
			resultado.setVotos(votos);
			return resultado.getVotos();
		}
		else {
			return resultado.getVotos();
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
   		try{
   			servicioGame.addReview(vt, review.getReview());
   		}
   		catch (NullPointerException ex){
   			throw new VotarAntesException();
   		}
   		return vt;
   	}
    
    @PostMapping("/usuario/{user}/comentario")
	public Comentario sendMessage(@PathVariable String user, @RequestBody ComentarioDTO message) {
		Usuario userReceptor = servicioUser.getByMail(user);
		Usuario userEmisor = servicioUser.getByMail(message.getCorreo());
		if (message.getTexto().length()<2) {
			throw new MensajeException(message.getTexto());
		}
		else if (userReceptor == null) {
			throw new UsuarioNotFoundException(user);
		} 
		else if (userEmisor == null) {
			throw new UsuarioNotFoundException(message.getCorreo());
		} 
		String mensaje= "Comentario de "+userEmisor.getCorreo()+" para ti:\n"+message.getTexto()+"\n"+"Enviado el ";
		Comentario comi = servicioUser.sendComentario(mensaje, message.getCorreo(), user);
		return comi;
	}
    
    @PutMapping("/usuario/{user}/comentario/{ref}")
	public Comentario updatedMessage(@PathVariable String user, @PathVariable long ref, @RequestBody ActualizaMensajeDTO message) {
		Usuario userReceptor = servicioUser.getByMail(user);
		if (message.getMensaje().length()<2) {
			throw new MensajeException(message.getMensaje());
		}
		else if (userReceptor == null) {
			throw new UsuarioNotFoundException(user);
		} 
		Comentario aux = servicioUser.updateComentario(user, message.getMensaje(), ref);
		if (aux==null) {
			throw new ComentarioException();
		}
		else {
			return aux; 
		}
    	
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
	
	@ExceptionHandler(ForbiddenVotosException.class)
	public ResponseEntity<ApiError> handleForbiddenVotos(ForbiddenVotosException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.FORBIDDEN);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
	}
	
	@ExceptionHandler(MensajeException.class)
	public ResponseEntity<ApiError> handleBadMessage(MensajeException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.FORBIDDEN);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
	}
	
	@ExceptionHandler(VotarAntesException.class)
	public ResponseEntity<ApiError> handleBadMessage(VotarAntesException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler(VotoException.class)
	public ResponseEntity<ApiError> handleForbiddenVotos(VotoException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler(ComentarioException.class)
	public ResponseEntity<ApiError> handleComentarioError(ComentarioException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

}
