package com.example.demo.dto;

import java.io.Serializable;

public class ActualizaMensajeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9176634427698884047L;
	private String texto;
	
	
	public ActualizaMensajeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

	

}
