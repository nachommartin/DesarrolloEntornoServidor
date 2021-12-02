package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;


public class Usuario {
	private String nick;
	private String password;
	private String nombre;
	private String direccion;
	private String telefono;
	private HashSet<Pedido> listaPedidos;
	
	
	public Usuario(String nick, String password, String nombre, String direccion, String telefono) {
		super();
		this.nick = nick;
		this.password = password;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.listaPedidos = new HashSet<Pedido>();

	}


	@Override
	public int hashCode() {
		return Objects.hash(nick);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nick, other.nick);
	}


	public String getNick() {
		return nick;
	}


	public String getPassword() {
		return password;
	}


	public String getNombre() {
		return nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public HashSet<Pedido> getListaPedidos() {
		return listaPedidos;
	}
	
	
	
	

	
	
}
