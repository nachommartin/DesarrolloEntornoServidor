package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.LoginException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.LoginCredentials;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JWTUtil;
import com.example.demo.services.UsuarioService;

@RestController
public class AuthController {
	
	@Autowired private UsuarioRepository userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;
    @Autowired private UsuarioService servicioUser; 
    
	@GetMapping("/register")
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
	
    
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Usuario user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepo.save(user);
        String token = jwtUtil.generateToken(user.getCorreo());
        return Collections.singletonMap("access_token", token);
    }
	
	 @PostMapping("/login")
	    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
	        try {
	            UsernamePasswordAuthenticationToken authInputToken =
	                    new UsernamePasswordAuthenticationToken(body.getCorreo(), body.getPassword());

	            authManager.authenticate(authInputToken);

	            String token = jwtUtil.generateToken(body.getCorreo());

	            return Collections.singletonMap("access_token", token);
	        }catch (AuthenticationException authExc){
	            throw new LoginException();
	        }
	    }
	 
		@ExceptionHandler(LoginException.class)
		public ResponseEntity<ApiError> handleBadLogin(LoginException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.BAD_REQUEST);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		}
	 


}
