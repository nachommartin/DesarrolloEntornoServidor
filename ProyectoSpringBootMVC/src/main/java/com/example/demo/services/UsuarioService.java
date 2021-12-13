package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private PedidoService servicioPed;
	
	@Autowired
	private ProductoService servicioPro;
	
	
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
	
	
	public void addPedido(Pedido pedido, String nick) {
		Usuario aux = this.getByNick(nick);
		if(aux.getListaPedidos().contains(pedido)) {
			aux.getListaPedidos().remove(pedido);
			aux.getListaPedidos().add(pedido);	
		}
		else {
			aux.getListaPedidos().add(pedido);
		}
	}
	
	public HashSet<Pedido> verPedidos(String nick) {
		Usuario aux = this.getByNick(nick);
		return aux.getListaPedidos();
	}
	
	public void borrarPedido(Pedido pedido, String nick) {
		Usuario aux= this.getByNick(nick);
		aux.getListaPedidos().remove(pedido);
	}
	
	public void actualizarPedido(Pedido pedidoOld, String nick, Pedido pedidoNew) {
		Usuario aux= this.getByNick(nick);
		aux.getListaPedidos().remove(pedidoOld);
		aux.getListaPedidos().add(pedidoNew);	
	}
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Usuario("nach85", "akira", "Nacho M. Martín", "Plaza Cronista 6, Sevilla", "656777888"),
						new Usuario("powerh", "kirby", "Hugo Mateo", "Plaza Cronista 6, Sevilla", "656111222"),
						new Usuario("nani98", "kanoute", "Fernando Campos", "Avenida Donantes de Sangre 23, Sevilla", "656444555")
						)
				);
		
		Pedido ped= new Pedido(324, "Plaza Cronista 6");
		Producto p1 = servicioPro.getByRef("007");
		Producto p2= servicioPro.getByRef("003");
		ped.calcularCosteTotal();
		servicioPed.addProductos(ped, p1, 1);
		servicioPed.addProductos(ped, p2, 1);
		addPedido(ped,"nach85");
		Pedido ped2= new Pedido(325, "Plaza Cronista 6");
		Producto p3 = servicioPro.getByRef("005");
		ped2.calcularCosteTotal();
		servicioPed.addProductos(ped2, p3, 2);
		addPedido(ped2,"nach85");
		
	}




}
