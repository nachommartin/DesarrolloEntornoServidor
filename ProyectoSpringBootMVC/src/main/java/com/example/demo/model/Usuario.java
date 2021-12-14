package com.example.demo.model;

import java.util.HashSet;
import java.util.Objects;

public class Usuario {
	
	private String nick;
	private String password;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private HashSet<Pedido> listaPedidos;
	
	
	public Usuario(String nick, String password, String nombre, String direccion, String telefono, String email) {
		super();
		this.nick = nick;
		this.password = password;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email= email;
		this.listaPedidos = new HashSet<Pedido>();

	}
	
	public Usuario() {
		super();
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

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
