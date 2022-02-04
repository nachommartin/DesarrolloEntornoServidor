package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.PedidoNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.services.PedidoService;
import com.example.demo.services.ProductoService;
import com.example.demo.services.UsuarioService;

@RestController
public class MainController {
	
	@Autowired
	private UsuarioService servicioUser;
	
	@Autowired 
	private ProductoService servicioProducto; 
	
	@Autowired
	private PedidoService servicioPedido;
	
	
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
	
	@GetMapping("/pedido/{ref}")
	public Pedido findByRef(@PathVariable long ref) {
		Pedido resultado = servicioPedido.getByRef(ref);
		if (resultado == null) {
			throw new PedidoNotFoundException(ref);
		} else {
			return resultado;
		}
	}
	
	@PostMapping("/pedido")
	public Pedido add(@RequestBody Pedido ped, String user){
		return this.servicioUser.addPedido(ped, user);
	}
	
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<ApiError> handlePedidoNoEncontrado(PedidoNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> handleUsuarioNoEncontrado(UsuarioNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
}
	
	


