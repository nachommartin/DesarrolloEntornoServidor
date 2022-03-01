package com.example.demo.dto;

import java.io.Serializable;

public class ComentarioDTO implements Serializable{

	
	private static final long serialVersionUID = -7471214343776999170L;
	
	private String correo;
	private String texto;
	
	
	
	
	public ComentarioDTO() {
		super();
	}

	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	
	

}
