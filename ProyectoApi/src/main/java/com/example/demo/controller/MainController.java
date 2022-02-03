package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

@RestController
public class MainController {
	
	@Autowired
	private UsuarioService servicioUser;
	
	@Autowired 
	private ProductoService servicioProducto; 
	
	
	
	@GetMapping("/usuario/{user}")
	public Usuario findByUser(@PathVariable String user) {
		Usuario resultado = servicioUser.getByNick(user);
		if (resultado == null) {
			throw new UsuarioNotFoundException(user);
		} else {
			return resultado;
		}
	}
	
	@GetMapping("/producto")
	public List<Producto> findAll(){
		return this.servicioProducto.mostrarProductos();
	}
	
	


}
