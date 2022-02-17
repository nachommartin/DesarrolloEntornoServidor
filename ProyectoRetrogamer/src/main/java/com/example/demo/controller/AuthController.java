package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginCredentials;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JWTUtil;

@RestController
public class AuthController {
	
	@Autowired private UsuarioRepository userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;
    
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
	            throw new RuntimeException("La contraseña o el correo proporcionado no son correctos");
	        }
	    }
	 
	 //409 Conflict para ver si el correo existe


}
