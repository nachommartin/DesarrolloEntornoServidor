package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import com.example.demo.model.Usuario;



public class UsuarioService {
	
	private List<Usuario> repositorio = new ArrayList<>();
	
	public Usuario getByNick(String nick) {
		Usuario resul = null;
		boolean bandera = false;
		int i = 0;
		while (!bandera && i<repositorio.size()) {
			if (repositorio.get(i).getNick().equals(nick)) {
				bandera = true;
				resul = repositorio.get(i);
			} else {
				i++;
			}
		}		
		return resul;
	}
	
	public boolean comprobarUsuario(String nick){
		boolean resul = false;
		int i = 0;
		while (!resul && i<repositorio.size()) {
			if (repositorio.get(i).getNick().equals(nick)) {
				resul = true;
			} else {
				i++;
			}
		}		
		return resul;
	}
	
	public boolean comprobarPass(String pass){
		boolean resul = false;
		int i = 0;
		while (!resul && i<repositorio.size()) {
			if (repositorio.get(i).getPassword().equals(pass)) {
				resul = true;
			} else {
				i++;
			}
		}		
		return resul;
	}
	
	public int comprobadorTotal(String usuario, String contrasena) {
		int resul; 
		if (this.comprobarUsuario(usuario) && this.comprobarPass(contrasena)==true) {
			resul= 1; 				
		}
		else if (this.comprobarUsuario(usuario)==true && this.comprobarPass(contrasena)==false){
			resul=0; 
		}
		else {
			resul=-1; 
		}
		return resul; 
	}

	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Usuario("nach85", "akira", "Nacho M. Martín", "Plaza Cronista 6, Sevilla", "656777888"),
						new Usuario("powerh", "kirby", "Hugo Mateo", "Plaza Cronista 6, Sevilla", "656111222"),
						new Usuario("nani98", "kanoute", "Fernando Campos", "Avenida Donantes de Sangre 23, Sevilla", "656444555")
						)
				);
		

	}
	
}
